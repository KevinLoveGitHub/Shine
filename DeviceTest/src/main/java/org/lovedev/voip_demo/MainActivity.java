package org.lovedev.voip_demo;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import com.telpo.telephony.support.helper.DeviceHelper;

import org.lovedev.util.ExecutorHelpers;
import org.lovedev.util.LogUtils;
import org.lovedev.util.UDPHelper;
import org.videolan.libvlc.IVLCVout;
import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private int count = 0;
    private int KEY_ACTION = -1;
    private String START_TALK = "start";
    private String STOP_TALK = "stop";
    private String DEFAULT = "等待呼叫";
    private String CALLING = "呼叫中，请摘机接听";
    private String IN_CALLING = "通话中，挂机结束通话";
    private String[] messages;
    private AlertDialog mAlertDialog;
    private LibVLC mLibVLC;
    private MediaPlayer mMediaPlayer;
    private String videoPath;
    private VideoView mVideoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupMediaPlayer();
        UDPHelper.openUDPPort(8899, new UDPHelper.UDPMessageListener() {
            @Override
            public void onMessageArrived(String s) {
                messages = s.split(",");
                LogUtils.d(TAG, "onMessageArrived: " + Arrays.toString(messages));
                if (START_TALK.equals(messages[0])) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            videoPath = messages[1];
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.dialog);
                            mAlertDialog = builder.create();

                            View view = View.inflate(getApplicationContext(), R.layout.dialog_call, null);
                            if (mVideoView == null) {
                                mVideoView = view.findViewById(R.id.video_view);
                            }
                            mAlertDialog.setView(view);
                            mAlertDialog.show();
                            mAlertDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                                @Override
                                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                                    int action = event.getAction();
                                    if (KeyEvent.ACTION_DOWN == action) {
                                        onKeyDown(keyCode, event);
                                    }

                                    if (KeyEvent.ACTION_UP == action) {
                                        onKeyUp(keyCode, event);
                                    }
                                    return false;
                                }
                            });
                        }
                    });
                }
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        int action = event.getAction();
        int code = event.getKeyCode();
        if (code == KeyEvent.KEYCODE_F8 && KEY_ACTION != KeyEvent.ACTION_DOWN) {
            Log.d(TAG, "onKeyDown: " + event.getAction() + "keyCode: " + event.getKeyCode());
            startTalk();
            KEY_ACTION = action;
            DeviceHelper.switch2Handle();
        }
        return false;
    }

    private void setupMediaPlayer() {
        mLibVLC = new LibVLC(this);
    }


    private void showVideo(final String path, final SurfaceView videoView) {
        videoView.post(new Runnable() {
            @Override
            public void run() {
                final int width = videoView.getWidth();
                final int height = videoView.getHeight();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Media media = new Media(mLibVLC, Uri.parse(path));
                        media.setHWDecoderEnabled(true, true);
                        mMediaPlayer = new MediaPlayer(media);
                        IVLCVout vlcVout = mMediaPlayer.getVLCVout();
                        vlcVout.setVideoView(videoView);
                        vlcVout.setWindowSize(width, height);
                        vlcVout.attachViews();
                        mMediaPlayer.setVideoTrackEnabled(true);
                        mMediaPlayer.play();
                    }
                });
            }
        });
    }

    private void stopPlayStream() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    private void startTalk() {
        videoPath = String.format("shine_net://tcp@%s:7799?cache=400", videoPath);
        showVideo(videoPath, mVideoView);
        ExecutorHelpers.getNetworkIO().execute(new Runnable() {
            @Override
            public void run() {
                SuClient suClient = new SuClient();
                suClient.init(null);
                suClient.execCMD("talk -p 7000 -ic 0 -id 0 -il 1 -oc 0 -od 12 -ol 1");
            }
        });
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        int action = event.getAction();
        Log.d(TAG, "onKeyUp: " + event.getAction() + "keyCode: " + keyCode);
        int code = event.getKeyCode();
        if (code == KeyEvent.KEYCODE_F8) {
            KEY_ACTION = action;
            mAlertDialog.dismiss();
            UDPHelper.sendUDPMessage("quit", "127.0.0.1", 12300);
            stopPlayStream();
            DeviceHelper.switch2HandsFree();
//            if (messages.length > 1) {
//                UDPHelper.sendUDPMessage(STOP_TALK, messages[1], Integer.parseInt(messages[2]));
//            }
        }
        return super.onKeyUp(keyCode, event);
    }


    public void getState(View view) {
        startTalk();
        mAlertDialog.show();
        if (count % 2 == 1) {
            DeviceHelper.switch2Handle();
            ((Button) view).setText("手柄");
        } else {
            DeviceHelper.switch2HandsFree();
            ((Button) view).setText("免提");
        }
        count++;
    }
}

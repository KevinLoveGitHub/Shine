package org.lovedev.vlcpreview;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Chronometer;
import android.widget.EditText;

import org.videolan.libvlc.IVLCVout;
import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;

public class MainActivity extends AppCompatActivity {

    private LibVLC mLibVLC;
    private int mWScreen;
    private int mHScreen;
    private MediaPlayer mMediaPlayer;
    private final int START_VIDEO = 0x001;
    private final int STOP_VIDEO = 0x002;
    private Chronometer mTime;

    @SuppressLint("HandlerLeak")
    private Handler mHandler  = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case START_VIDEO:
                    startPreview(null);
                    break;
                case STOP_VIDEO:
                    stopPreview(null);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTime = findViewById(R.id.time);
        setupMediaPlayer();
    }

    private void setupMediaPlayer() {
        mLibVLC = new LibVLC(this);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        mWScreen = dm.widthPixels;
        mHScreen = dm.heightPixels;

    }

    /**
     * 开始播放
     * @param path  视频流的路径 shine_net://tcp@172.168.2.156:5020?dec=hard?mode=quene?cache=300?fps=25
     * @param videoView 播放视频的控件
     */
    private void showVideo(final String path, SurfaceView videoView) {
        Media media = new Media(mLibVLC, Uri.parse(path));
        media.setHWDecoderEnabled(true, true);
        mMediaPlayer = new MediaPlayer(media);
        IVLCVout vlcVout = mMediaPlayer.getVLCVout();
        vlcVout.setVideoView(videoView);
        vlcVout.setWindowSize(mWScreen, mHScreen);
        vlcVout.attachViews();

        mMediaPlayer.setVideoTrackEnabled(true);
        mMediaPlayer.play();
        mMediaPlayer.setEventListener(mEventListener);
    }

    private void stopPlayStream() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    private MediaPlayer.EventListener mEventListener = new MediaPlayer.EventListener() {
        @Override
        public void onEvent(MediaPlayer.Event event) {

        }
    };


    public void startPreview(View view) {
        String address = ((EditText) findViewById(R.id.et)).getText().toString().trim();
        SurfaceView videoView = findViewById(R.id.videoView);
        showVideo(address, videoView);
        mHandler.sendEmptyMessageDelayed(STOP_VIDEO, 10 * 60 * 1000);
        mTime.setBase(SystemClock.elapsedRealtime());
        mTime.start();
    }

    public void stopPreview(View view) {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
        }
        mHandler.sendEmptyMessageDelayed(START_VIDEO, 1000);
    }
}

package com.shine.webrtclibrary;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.util.Log;
import android.view.SurfaceView;
import android.widget.LinearLayout;

import org.webrtc.videoengine.ViERenderer;
import org.webrtc.videoengineapp.IViEAndroidCallback;
import org.webrtc.videoengineapp.ViEAndroidJavaAPI;

/**
 * @className: WebVideoRtcUtils
 * @desc:
 * @author: Jiangcy
 * @datetime: 2017/2/24
 */
public class WebVideoRtcUtils {
    private static final String TAG = "WebRtcUtils";
    private ViEAndroidJavaAPI vieAndroidAPI = null;

    // remote renderer
    private SurfaceView remoteSurfaceView = null;

    // flags
    private boolean viERunning = false;
    private boolean voERunning = false;

    // channel number
    private int channel = -1;
    private int cameraId;
    private int voiceChannel = -1;

    private int receivePortVoice = 11113;
    private int destinationPortVoice = 11113;

    private int receivePortVideo = 11111;
    private int destinationPortVideo = 11111;
    private LinearLayout mLlRemoteSurface = null;
    private Context mContext;
    private String[] mVideoCodecsSizeStrings = {
            "176x144", "320x240",
            "352x288", "640x480"
    };

    private String[] mVoiceCodecsStrings = null;
    private String[] mVideoCodecsStrings = null;
    private String[] mRenderTypes = {
            "OPENGL ", "SURFACE ", "MEDIACODEC"
    };

    public enum RenderType {
        OPENGL,
        SURFACE,
        MEDIACODEC
    }

    private RenderType renderType = RenderType.OPENGL;

    //编码设置
    private int codecType = 0;
    private int codecSize = 2;
    private int voiceCodecType = 0;
    private int codecSizeWidth = 0;
    private int codecSizeHeight = 0;
    private int RECEIVE_CODEC_FRAMERATE = 15;
    private int SEND_CODEC_FRAMERATE = 15;
    private int INIT_BITRATE = 500;
    //是否使用前置摄像头
    private boolean usingFrontCamera = false;
    private boolean enableNack = true;
    private boolean enableAECM = true;
    private boolean enableAGC = true;
    private boolean enableNS = true;
    private int volumeLevel = 100;
    private boolean isInit = false;
    // debug
    private boolean enableTrace = false;
    private long startSpeakTime = 0;
    private int callVol=50;
    private int musicMax;
    private AudioManager mAudioManager;


    public void startSpeak(Context context,String remoteIP,LinearLayout mLlRemoteSurface){
        startCall(context, remoteIP, mLlRemoteSurface, true, null);
    }

    public void startVideoWithoutVoice(Context context,String remoteIP,LinearLayout layout) {
        startCall(context, remoteIP, layout, false, null);
    }

    public void startVideoWithoutVoice(Context context,String remoteIP,LinearLayout layout, IViEAndroidCallback callback) {
        startCall(context, remoteIP, layout, false, callback);
    }

    public void startVideo(Context context, String remoteIP, LinearLayout layout, IViEAndroidCallback callback) {
        startCall(context, remoteIP, layout, false, callback);
    }

    private void startCall(Context context, String remoteIP, LinearLayout mLlRemoteSurface, boolean enableVoice, IViEAndroidCallback callback) {
        stopCall();
        init(context);
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, callVol * musicMax / 100, 0);
        startSpeakTime = System.currentTimeMillis();
        this.mLlRemoteSurface = mLlRemoteSurface;
        startCall(remoteIP, true, enableVoice, true, true, callback);
    }

    public void setCallVol(int callVol) {
        this.callVol = callVol;
    }

    public void stopCall() {

        stopAll();
    }


    public long getStartSpeakTime() {
        return startSpeakTime;
    }
    public boolean isSpeaking() {
        return voERunning;
    }

    private void initVieAndroidAPI(Context context) {
        if (null == vieAndroidAPI) {
            vieAndroidAPI = new ViEAndroidJavaAPI(context);
        }
        int setupVoE = setupVoE();
        int videoEngine = vieAndroidAPI.GetVideoEngine();
        int init = vieAndroidAPI.Init(enableTrace);
        Log.e(TAG, "setupVoE  = "
                + setupVoE
                + "   VideoEngine = "
                + videoEngine
                + "   init = "
                + init);
        if (0 > setupVoE || 0 > videoEngine ||
                0 > init) {
            // Show dialog
            AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
            alertDialog.setTitle("WebRTC Error");
            alertDialog.setMessage("Can not init video engine.");
            alertDialog.setButton(
                    DialogInterface.BUTTON_POSITIVE,
                    "OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    });
            alertDialog.show();
        }
        // cleaning
        remoteSurfaceView = null;

        // Video codec
        mVideoCodecsStrings = vieAndroidAPI.GetCodecs();
        Log.e(TAG, "===   VideoCodecsStrings  === " + mVideoCodecsStrings);
        for (String codes :
                mVideoCodecsStrings) {
            Log.e(TAG, "mVideoCodecsStrings  = " + codes);
        }
        Log.e(TAG, "===   VoiceCodecsStrings  === ");
        // Voice codec
        mVoiceCodecsStrings = vieAndroidAPI.VoE_GetCodecs();
        for (String codes :
                mVoiceCodecsStrings) {
            Log.e(TAG, "mVoiceCodecsStrings  = " + codes);
        }
    }

    public void init(Context context ) {
        init(context,renderType, true, true, true, true);
    }

    public void init(Context context,
                     RenderType renderType,
                     boolean enableNack,
                     boolean enableAECM, boolean enableAGC, boolean enableNS) {
        mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        musicMax = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        this.enableNack = enableNack;
        this.enableAECM = enableAECM;
        this.enableNS = enableNS;
        this.enableAGC = enableAGC;
        this.renderType = renderType;
        mContext = context;
        initVieAndroidAPI(mContext);
        isInit = true;
    }

    public void startCall(String remoteIP, boolean isSpeaker, boolean enableVoice,
                          boolean enableVideoSend, boolean enableVideoReceive,IViEAndroidCallback callback) {
        if (!isInit) {
            throw new RuntimeException("WebRtcUtils is not Init");
        }
        if (remoteIP.isEmpty()) {
            throw new NullPointerException("remoteIP is null");
        }
        stopAll();

        initVieAndroidAPI(mContext);

        start(remoteIP, isSpeaker, enableVoice, enableVideoSend, enableVideoReceive,callback);
    }



    public void release() {
        isInit = false;
        stopAll();
    }

    private void start(String remoteIP, boolean isSpeaker, boolean enableVoice,
                       boolean enableVideoSend, boolean enableVideoReceive,IViEAndroidCallback callback) {
        String sCodecSize = mVideoCodecsSizeStrings[codecSize];
        String[] aCodecSize = sCodecSize.split("x");
        codecSizeWidth = Integer.parseInt(aCodecSize[0]);
        codecSizeHeight = Integer.parseInt(aCodecSize[1]);
        boolean enableVideo = enableVideoSend || enableVideoReceive;
        //开启Voice
        if (enableVoice) {
            startVoiceEngine(isSpeaker, volumeLevel, remoteIP, enableAECM, enableAGC, enableNS);
        }

        if (enableVideo) {
            startVideoEngine(remoteIP, enableVideoSend, enableVideoReceive, renderType, enableNack,callback);
        }
    }

    private void routeAudio(boolean enableSpeaker) {
        if (0 != vieAndroidAPI.VoE_SetLoudspeakerStatus(enableSpeaker)) {
            Log.d(TAG, "VoE set louspeaker status failed");
        }
    }

    private void stopAll() {
        Log.d(TAG, "stopAll");

        if (vieAndroidAPI != null) {

            if (voERunning) {
                voERunning = false;
                stopVoiceEngine();
            }

            if (viERunning) {
                viERunning = false;
                vieAndroidAPI.StopRender(channel);
                vieAndroidAPI.StopReceive(channel);
                vieAndroidAPI.StopSend(channel);
                vieAndroidAPI.RemoveRemoteRenderer(channel);
                vieAndroidAPI.ViE_DeleteChannel(channel);
                channel = -1;
                vieAndroidAPI.StopCamera(cameraId);
                vieAndroidAPI.Terminate();
                mLlRemoteSurface.removeView(remoteSurfaceView);
                remoteSurfaceView = null;
            }
        }
    }

    private int startVoiceEngine(boolean isSpeaker, int volumeLevel, String remoteIP,
                                 boolean enableAECM, boolean enableAGC, boolean enableNS) {
        // Create channel
        voiceChannel = vieAndroidAPI.VoE_CreateChannel();
        if (0 > voiceChannel) {
            Log.d(TAG, "VoE create channel failed");
            return -1;
        }

        // Set local receiver
        if (0 != vieAndroidAPI.VoE_SetLocalReceiver(voiceChannel,
                receivePortVoice)) {
            Log.d(TAG, "VoE set local receiver failed");
        }

        if (0 != vieAndroidAPI.VoE_StartListen(voiceChannel)) {
            Log.d(TAG, "VoE start listen failed");
        }

        // Route audio
        routeAudio(isSpeaker);

        // set volume to default value
        if (0 != vieAndroidAPI.VoE_SetSpeakerVolume(volumeLevel)) {
            Log.d(TAG, "VoE set speaker volume failed");
        }

        // Start playout
        if (0 != vieAndroidAPI.VoE_StartPlayout(voiceChannel)) {
            Log.d(TAG, "VoE start playout failed");
        }

        if (0 != vieAndroidAPI.VoE_SetSendDestination(voiceChannel,
                destinationPortVoice, remoteIP)) {
            Log.d(TAG, "VoE set send  destination failed");
        }

        if (0 != vieAndroidAPI.VoE_SetSendCodec(voiceChannel, voiceCodecType)) {
            Log.d(TAG, "VoE set send codec failed");
        }

        if (0 != vieAndroidAPI.VoE_SetECStatus(enableAECM)) {
            Log.d(TAG, "VoE set EC Status failed");
        }

        if (0 != vieAndroidAPI.VoE_SetAGCStatus(enableAGC)) {
            Log.d(TAG, "VoE set AGC Status failed");
        }

        if (0 != vieAndroidAPI.VoE_SetNSStatus(enableNS)) {
            Log.d(TAG, "VoE set NS Status failed");
        }

        if (0 != vieAndroidAPI.VoE_StartSend(voiceChannel)) {
            Log.d(TAG, "VoE start send failed");
        }

        voERunning = true;
        return 0;
    }

    private int startVideoEngine(String remoteIP,
                                 boolean enableVideoSend, boolean enableVideoReceive, RenderType renderType,
                                 boolean enableNack,IViEAndroidCallback callback ) {
        int ret = 0;


        channel = vieAndroidAPI.CreateChannel(voiceChannel);
        ret = vieAndroidAPI.SetLocalReceiver(channel,
                receivePortVideo);
        ret = vieAndroidAPI.SetSendDestination(channel,
                destinationPortVideo,
                remoteIP);

        if (enableVideoReceive) {
            if (renderType == RenderType.OPENGL) {
                Log.v(TAG, "Create OpenGL Render");
                remoteSurfaceView = ViERenderer.CreateRenderer(mContext, true);
            } else if (renderType == RenderType.SURFACE) {
                Log.v(TAG, "Create SurfaceView Render");
                remoteSurfaceView = ViERenderer.CreateRenderer(mContext, false);
            } else if (renderType == RenderType.MEDIACODEC) {
                Log.v(TAG, "Create MediaCodec Decoder/Renderer");
                remoteSurfaceView = new SurfaceView(mContext);
            }

            if (mLlRemoteSurface != null) {
                mLlRemoteSurface.addView(remoteSurfaceView);
            }

            if (renderType == RenderType.MEDIACODEC) {
                ret = vieAndroidAPI.SetExternalMediaCodecDecoderRenderer(
                        channel, remoteSurfaceView);
            } else {
                ret = vieAndroidAPI.AddRemoteRenderer(channel, remoteSurfaceView);
            }

            ret = vieAndroidAPI.SetReceiveCodec(channel,
                    codecType,
                    INIT_BITRATE,
                    codecSizeWidth,
                    codecSizeHeight,
                    RECEIVE_CODEC_FRAMERATE);
            ret = vieAndroidAPI.StartRender(channel);
            ret = vieAndroidAPI.StartReceive(channel);
        }

        if (enableVideoSend) {
            ret = vieAndroidAPI.SetSendCodec(channel, codecType, INIT_BITRATE,
                    codecSizeWidth, codecSizeHeight, SEND_CODEC_FRAMERATE);
            int camId = vieAndroidAPI.StartCamera(channel, usingFrontCamera ? 1 : 0);
            Log.e(TAG, "startVideoEngine: camId " + camId);
            if (camId >= 0) {
                cameraId = camId;
                //设置摄像头 旋转角度
                vieAndroidAPI.SetRotation(cameraId, 270);
            } else {
                ret = camId;
            }
            ret = vieAndroidAPI.StartSend(channel);
        }

        // TODO(leozwang): Add more options besides PLI, currently use pli
        // as the default. Also check return value.
        ret = vieAndroidAPI.EnablePLI(channel, true);
        ret = vieAndroidAPI.EnableNACK(channel, enableNack);
        if (callback != null) {
            ret = vieAndroidAPI.SetCallback(channel, callback);
        }
        viERunning = true;
        return ret;
    }

    private void stopVoiceEngine() {
        // Stop send
        if (0 != vieAndroidAPI.VoE_StopSend(voiceChannel)) {
            Log.d(TAG, "VoE stop send failed");
        }

        // Stop listen
        if (0 != vieAndroidAPI.VoE_StopListen(voiceChannel)) {
            Log.d(TAG, "VoE stop listen failed");
        }

        // Stop playout
        if (0 != vieAndroidAPI.VoE_StopPlayout(voiceChannel)) {
            Log.d(TAG, "VoE stop playout failed");
        }

        if (0 != vieAndroidAPI.VoE_DeleteChannel(voiceChannel)) {
            Log.d(TAG, "VoE delete channel failed");
        }
        voiceChannel = -1;

        // Terminate
        if (0 != vieAndroidAPI.VoE_Terminate()) {
            Log.d(TAG, "VoE terminate failed");
        }
    }

    private int setupVoE() {
        // Create VoiceEngine
        // Error logging is done in native API wrapper
        vieAndroidAPI.VoE_Create(mContext);

        // Initialize
        if (0 != vieAndroidAPI.VoE_Init(enableTrace)) {
            Log.d(TAG, "VoE init failed");
            return -1;
        }

        return 0;
    }

    public String[] getVideoCodecsSizeStrings() {
        return mVideoCodecsSizeStrings;
    }

    public String[] getVoiceCodecsStrings() {
        return mVoiceCodecsStrings;
    }

    public String[] getVideoCodecsStrings() {
        return mVideoCodecsStrings;
    }

    public String[] getRenderTypes() {
        return mRenderTypes;
    }

    public void setSetting(int codecType, int voiceCodecType, int codecSize,
                           RenderType renderType) {
        this.codecType = codecType;
        this.voiceCodecType = voiceCodecType;
        this.codecSize = codecSize;
        this.renderType = renderType;
    }

    public int getFramerate() {
        return SEND_CODEC_FRAMERATE;
    }

    public void setFramerate(int framerate) {
        this.SEND_CODEC_FRAMERATE = framerate;
        this.RECEIVE_CODEC_FRAMERATE = framerate;
    }

    public int getBitrate() {
        return INIT_BITRATE;
    }

    public void setBitrate(int INIT_BITRATE) {
        this.INIT_BITRATE = INIT_BITRATE;
    }


}

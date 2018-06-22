package com.shine.webrtclibrary;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.util.Log;

import org.webrtc.videoengineapp.ViEAndroidJavaAPI;

/**
 * @className: WebAudioRtcUtils
 * @desc:
 * @author: Jiangcy
 * @datetime: 2017/2/24
 */
public class WebAudioRtcUtils {
    private ViEAndroidJavaAPI vieAndroidAPI = null;
    private final String TAG = "WebRtcUtils";
    private int voiceChannel = -1;
    private int receivePortVoice = 11113;
    private int destinationPortVoice = 11113;
    private int voiceCodecType = 0;
    private int volumeLevel = 300;
    private boolean enableTrace = true;
    private boolean enableAECM = true;
    private boolean enableAGC = true;
    private boolean enableNS = true;
    private boolean voERunning = false;
    private Context mContext;
    private long startSpeakTime = 0;
    private int callVol=50;
    private int musicMax;
    private AudioManager mAudioManager;

    public void init(Context context) {
        this.mContext = context;
        mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        musicMax = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        if (null == vieAndroidAPI) {
            vieAndroidAPI = new ViEAndroidJavaAPI(mContext);
        }
        if (0 > setupVoE() || 0 > vieAndroidAPI.GetVideoEngine() ||
                0 > vieAndroidAPI.Init(enableTrace)) {
            // Show dialog
            AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
            alertDialog.setTitle("WebRTC Error");
            alertDialog.setMessage("Can not init video engine.");
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    return;
                }
            });
            alertDialog.show();
        }
    }

    public void setVolumeLevel(int volumeLevel) {
        this.volumeLevel = volumeLevel;
    }

    public void setCallVol(int callVol) {
        this.callVol = callVol;
    }

    public int startSpeak(Context context, String ip){
        return startCall(context, ip, System.currentTimeMillis());
    }
    public int startSpeak(Context context, String ip,long startTime){
        return startCall(context, ip, startTime);
    }

    public int startCall (Context context, String ip,long startTime) {
        Log.e(TAG, "startSpeak ip = " + ip);
        stopAll();
        init(context);
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, callVol * musicMax / 100, 0);
        startSpeakTime = startTime;
        // Create channel
        voiceChannel = vieAndroidAPI.VoE_CreateChannel();
        if (0 > voiceChannel) {
            Log.d(TAG, "VoE create channel failed");
            return -1;
        }

        // Set local receiver
        if (0 != vieAndroidAPI.VoE_SetLocalReceiver(voiceChannel, receivePortVoice)) {
            Log.d(TAG, "VoE set local receiver failed");
        }

        if (0 != vieAndroidAPI.VoE_StartListen(voiceChannel)) {
            Log.d(TAG, "VoE start listen failed");
        }

        // Route audio
        if (0 != vieAndroidAPI.VoE_SetLoudspeakerStatus(true)) {
            Log.d(TAG, "VoE set louspeaker status failed");
        }

        // set volume to default value
        if (0 != vieAndroidAPI.VoE_SetSpeakerVolume(volumeLevel)) {
            Log.d(TAG, "VoE set speaker volume failed");
        }

        // Start playout
        if (0 != vieAndroidAPI.VoE_StartPlayout(voiceChannel)) {
            Log.d(TAG, "VoE start playout failed");
        }

        if (0 != vieAndroidAPI.VoE_SetSendDestination(voiceChannel, destinationPortVoice, ip)) {
            Log.d(TAG, "VoE set send  destination failed");
        }
        //        mVoiceCodecsStrings = vieAndroidAPI.VoE_GetCodecs();
        //        根据这个获取所有可以发送的codec数组 默认第一个ISAC type:103 freq:16000 pac:480 ch:1 rate:32000
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

    public boolean isSpeaking() {
        return voERunning;
    }

    public void stopAll() {
        Log.d(TAG, "stopAll");
        startSpeakTime = 0;
        stopCall();
    }

    public void stopCall() {
        Log.d(TAG, "stopAll");
        if (vieAndroidAPI != null) {

            if (voERunning) {
                voERunning = false;
                stopVoiceEngine();
            }
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

    public long getStartSpeakTime() {
        return startSpeakTime;
    }
}

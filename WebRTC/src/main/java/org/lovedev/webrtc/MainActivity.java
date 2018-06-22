package org.lovedev.webrtc;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.shine.webrtclibrary.WebVideoRtcUtils;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mLayout;
    private WebVideoRtcUtils mWebVideoRtcUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLayout = (LinearLayout) findViewById(R.id.view);
        mWebVideoRtcUtils = new WebVideoRtcUtils();
        findFirstFrontFacingCamera();
    }

    public void stop(View view) {
        mWebVideoRtcUtils.stopCall();
    }

    public void start(View view) {
        EditText editText = (EditText) findViewById(R.id.et);
        final String trim = editText.getText().toString().trim();
        mWebVideoRtcUtils.startSpeak(getApplicationContext(), trim, mLayout, 0);
    }

    public void startAsService(View view) {
        EditText editText = (EditText) findViewById(R.id.et);
        String trim = editText.getText().toString().trim();
        mWebVideoRtcUtils.startSpeak(getApplicationContext(), trim, mLayout, 0);
        new Thread(new Runnable() {
            @Override
            public void run() {
                SuClient suClient = new SuClient();
                suClient.init(null);
                suClient.execCMD("talk -p 7000 -vol 1000 -ic 0 -id 0 -il 1 -oc 0 -od 12 -ol 1");
            }
        }).start();

    }

    public void startAsClient(View view) {
        EditText editText = (EditText) findViewById(R.id.et);
        final String trim = editText.getText().toString().trim();
        mWebVideoRtcUtils.startSpeak(getApplicationContext(), trim, mLayout, 0);
        new Thread(new Runnable() {
            @Override
            public void run() {
                SuClient suClient = new SuClient();
                suClient.init(null);
                String cmd = String.format(Locale.CHINA, "talk -m %s:7000  -vol 1000 -ic 0 -id 0 -il 1 -oc 0 -od 12 -ol 1", trim);
                suClient.execCMD(cmd);
            }
        }).start();
    }


    private int findFirstFrontFacingCamera() {
        int foundId = -1;
        // find the first front facing camera
        int numCams = Camera.getNumberOfCameras();
        for (int camId = 0; camId < numCams; camId++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(camId, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                Log.d("camera", "Found front facing camera");
                foundId = camId;
                break;
            }
        }
        return foundId;
    }
}

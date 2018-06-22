package org.lovedev.webrtc;

import android.Manifest;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.shine.webrtclibrary.WebVideoRtcUtils;

import org.lovedev.util.PermissionUtils;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_CAMERA = 0x001;
    private final String PERMISSION_CAMERA = Manifest.permission.CAMERA;
    private LinearLayout mLayout;
    private WebVideoRtcUtils mWebVideoRtcUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLayout = (LinearLayout) findViewById(R.id.view);
        mWebVideoRtcUtils = new WebVideoRtcUtils();
        PermissionUtils.checkAndRequestPermission(this, PERMISSION_CAMERA, REQUEST_CODE_CAMERA,
                new PermissionUtils.PermissionRequestSuccessCallBack() {
                    @Override
                    public void onHasPermission() {
                        // 权限已被授予
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (PermissionUtils.isPermissionRequestSuccess(grantResults)) { }
    }

    public void stop(View view) {
        mWebVideoRtcUtils.stopCall();
    }

    public void start(View view) {
        EditText editText = (EditText) findViewById(R.id.et);
        final String trim = editText.getText().toString().trim();
        mWebVideoRtcUtils.startVideoWithoutVoice(getApplicationContext(), trim, mLayout);
    }

    public void startAsService(View view) {
        EditText editText = (EditText) findViewById(R.id.et);
        String trim = editText.getText().toString().trim();
        mWebVideoRtcUtils.startVideoWithoutVoice(getApplicationContext(), trim, mLayout);
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(1000);
                SuClient suClient = new SuClient();
                suClient.init(null);
                suClient.execCMD("talk -p 7000 -vol 1000 -ic 0 -id 0 -il 1 -oc 0 -od 12 -ol 1");
            }
        }).start();

    }

    public void startAsClient(View view) {
        EditText editText = (EditText) findViewById(R.id.et);
        final String trim = editText.getText().toString().trim();
        mWebVideoRtcUtils.startVideoWithoutVoice(getApplicationContext(), trim, mLayout);
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(1000);
                SuClient suClient = new SuClient();
                suClient.init(null);
                String cmd = String.format(Locale.CHINA, "talk -m %s:7000  -vol 1000", trim);
                suClient.execCMD(cmd);
            }
        }).start();
    }

}

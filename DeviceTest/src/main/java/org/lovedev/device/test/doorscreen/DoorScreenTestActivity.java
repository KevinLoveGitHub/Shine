package org.lovedev.device.test.doorscreen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import org.lovedev.device.test.R;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import android_serialport_api.SerialPort;

public class DoorScreenTestActivity extends AppCompatActivity {
    private static final String PATH = "/dev/ttyS4";
    private static final String TAG = "DoorScreenTestActivity";
    private static final int BAUDRATE = 9600;
    private SerialPort mSerialPort;
    private OutputStream mOutputStream;

    /*
    <string name="long_red">7E1001000000000000000000000000010103000012AA</string>
    <string name="long_blue">7E1001000000000000000000000000010203000011AA</string>
    <string name="long_green">7E1001000000000000000000000000010303000010AA</string>
    <string name="long_orange">7E1001000000000000000000000000010403000017AA</string>

    <string name="flash_red">7E1001000000000000000000000000010102010012AA</string>
    <string name="flash_blue">7E1001000000000000000000000000010202010011AA</string>
    <string name="flash_green">7E1001000000000000000000000000010302010010AA</string>
    <string name="flash_orange">7E1001000000000000000000000000010402010017AA</string>

    <string name="slow_flash_red">7E1001000000000000000000000000010102020011AA</string>
    <string name="slow_flash_blue">7E1001000000000000000000000000010202020012AA</string>
    <string name="slow_flash_green">7E1001000000000000000000000000010302020013AA</string>
    <string name="light_off">7E1001000000000000000000000000010001000011AA</string>
     */

    private final String longRed = "7E1001000000000000000000000000010103000012AA";
    private final String longBlue = "7E1001000000000000000000000000010203000011AA";
    private final String longGreen = "7E1001000000000000000000000000010303000010AA";
    private final String longOrange = "7E1001000000000000000000000000010403000017AA";

    private final String flashRed = "7E1001000000000000000000000000010102010012AA";
    private final String flashBlue = "7E1001000000000000000000000000010202010011AA";
    private final String flashGreen = "7E1001000000000000000000000000010302010010AA";
    private final String flashOrange = "7E1001000000000000000000000000010402010017AA";

    private final String destroyLight = "7E1001000000000000000000000000010001000011AA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door_screen_test);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        init();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        File file = new File(PATH);
        if (!file.canRead() || !file.canWrite()) {
            RootCommand rootCommand = new RootCommand();
            rootCommand.grand(file.getAbsolutePath());
        }
        Log.d(TAG, "can Read " + file.canRead() + "canWrite" + file.canWrite());
        if (file.canRead() && file.canWrite()) {
            mSerialPort = new SerialPort(file, BAUDRATE, 0);
            mOutputStream = mSerialPort.getOutputStream();
        } else {
            Log.e(TAG, "打开串口/dev/ttyS4失败");
        }
    }

    public void handleInstruction(String instruction) {
        try {
            if (mOutputStream == null) {
                init();
            }
            if (mOutputStream != null) {
                mOutputStream.write(hex2Bytes(instruction));
                mOutputStream.write('\n');
            }
        } catch (IOException e) {
            Log.d(TAG, e.toString());
        }
    }

    //十六进制转字节数组
    private byte[] hex2Bytes(String src) {
        byte[] res = new byte[src.length() / 2];
        char[] chs = src.toCharArray();
        int[] b = new int[2];

        for (int i = 0, c = 0; i < chs.length; i += 2, c++) {
            for (int j = 0; j < 2; j++) {
                if (chs[i + j] >= '0' && chs[i + j] <= '9') {
                    b[j] = (chs[i + j] - '0');
                } else if (chs[i + j] >= 'A' && chs[i + j] <= 'F') {
                    b[j] = (chs[i + j] - 'A' + 10);
                } else if (chs[i + j] >= 'a' && chs[i + j] <= 'f') {
                    b[j] = (chs[i + j] - 'a' + 10);
                }
            }
            b[0] = (b[0] & 0x0f) << 4;
            b[1] = (b[1] & 0x0f);
            res[c] = (byte) (b[0] | b[1]);
        }

        return res;
    }

    public void red(View view) {
        handleInstruction(longRed);
    }

    public void orange(View view) {
        handleInstruction(longOrange);
    }

    public void blue(View view) {
        handleInstruction(longBlue);
    }

    public void green(View view) {
        handleInstruction(longGreen);
    }

    public void destroy(View view) {
        handleInstruction(destroyLight);
    }

    public void redFlash(View view) {
        handleInstruction(flashRed);
    }

    public void orangeFlash(View view) {
        handleInstruction(flashOrange);
    }

    public void blueFlash(View view) {
        handleInstruction(flashBlue);
    }

    public void greenFlash(View view) {
        handleInstruction(flashGreen);
    }

    public void back(View view) {
        finish();
    }
}

package org.lovedev.shine;

import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class VibratorActivity extends AppCompatActivity {

    private Vibrator mVibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vibrator);
        mVibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.start:
                // 停止 开启 停止 开启
                long[] pattern = {100, 400, 100, 400};
                mVibrator.vibrate(pattern,1);
                break;
            case R.id.stop:
                mVibrator.cancel();
                break;
                default:
                    break;
        }

    }
}

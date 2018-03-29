package org.lovedev.shine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void navigate(View view) {
        switch (view.getId()) {
            case R.id.Vibrator:
                startActivity(new Intent(this,VibratorActivity.class));
                break;
            default:
                break;
        }
    }
}

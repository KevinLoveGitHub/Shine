package org.lovedev.device.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.lovedev.device.test.doorscreen.DoorScreenTestActivity;
import org.lovedev.device.test.tenphone.TenTestActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void tenTest(View view) {
        startActivity(new Intent(this, TenTestActivity.class));
    }

    public void doorScreenTest(View view) {
        startActivity(new Intent(this, DoorScreenTestActivity.class));
    }
}

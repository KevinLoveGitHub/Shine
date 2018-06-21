package org.lovedev.tcp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SuClient suClient = new SuClient();
        suClient.init(null);
        suClient.execCMD("sh /sdcard/shine/talk/config_talk.sh");
    }
}

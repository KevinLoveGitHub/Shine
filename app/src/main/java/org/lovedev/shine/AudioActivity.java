package org.lovedev.shine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.SeekBar;

public class AudioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        SeekBar seekBar = findViewById(R.id.sb);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                AudioManagerHelper audioMngHelper = new AudioManagerHelper(AudioActivity.this);
                Log.d("onProgressChanged", "Progress: "  + i + " get100CurrentVolume: " + audioMngHelper.get100CurrentVolume());
                audioMngHelper.setAudioType(AudioManagerHelper.TYPE_RING);
                audioMngHelper.setVoice100(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}

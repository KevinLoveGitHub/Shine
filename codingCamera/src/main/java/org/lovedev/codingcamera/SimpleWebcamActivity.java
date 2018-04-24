package org.lovedev.codingcamera;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.lovedev.util.ExecutorHelpers;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SimpleWebcamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_webcam);
        CameraPreview cp = (CameraPreview) findViewById(R.id.cp);
    }

    public void stop(View view) {
        String strInfo = "sonixcamerastop";
        execute(strInfo);
    }

    public void start(View view) {
        String strInfo = "sonixcamerastart 30 2000 1 0 ";
        execute(strInfo);
    }


    public void execute(final String instruction) {
        ExecutorHelpers.getNetworkIO().execute(new Runnable() {
            @Override
            public void run() {
                DatagramSocket ds = null;
                try {
                    ds = new DatagramSocket();
                    ds.send(new DatagramPacket(instruction.getBytes(), instruction.length(),
                            InetAddress.getByName("127.0.0.1"), 1888));
                    ds.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

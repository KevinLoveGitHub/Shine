package org.lovedev.codingcamera;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void stop(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                DatagramSocket ds = null;
                try {
                    ds = new DatagramSocket();
                    String strInfo = "sonixcamerastop";
                    ds.send(new DatagramPacket(strInfo.getBytes(), strInfo.length(),
                            InetAddress.getByName("127.0.0.1"), 1888));
                    ds.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void start(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                DatagramSocket ds = null;
                try {
                    ds = new DatagramSocket();
                    String strInfo = "sonixcamerastart 30 2000 1 0 ";
                    ds.send(new DatagramPacket(strInfo.getBytes(), strInfo.length(),
                            InetAddress.getByName("127.0.0.1"), 1888));
                    ds.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

package org.lovedev.udpport;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import org.lovedev.util.ExecutorHelpers;
import org.lovedev.util.LogUtils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private DatagramSocket mDatagramSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void initUDP(View view) {
        ExecutorHelpers.getNetworkIO().execute(new Runnable() {
            @Override
            public void run() {
                final int PORT = 52300;
                // 定义每个数据报的最大大小为4KB
                final int DATA_LEN = 4096;
                // 定义接收网络数据的字节数组
                byte[] inBuff = new byte[DATA_LEN];
                // 以指定字节数组创建准备接收数据的DatagramPacket对象
                DatagramPacket inPacket = new DatagramPacket(inBuff, inBuff.length);
                // 创建DatagramSocket对象
                try {
                    if (mDatagramSocket == null) {
                        mDatagramSocket = new DatagramSocket(null);
                        mDatagramSocket.setReuseAddress(true);
                        mDatagramSocket.bind(new InetSocketAddress(PORT));
                    }
                    // 采用循环接收数据
                    while (mDatagramSocket != null && !mDatagramSocket.isClosed()) {
                        // 读取Socket中的数据，读到的数据放入inPacket封装的数组里
                        mDatagramSocket.receive(inPacket);
                        String msg = new String(inBuff, 0, inPacket.getLength());
                        LogUtils.i(TAG, msg);
                    }
                } catch (IOException e) {
                    LogUtils.d(TAG, "run: " + e.getMessage());
                }

            }
        });
    }

    public void send(View view) {
        ExecutorHelpers.getNetworkIO().execute(new Runnable() {

            @Override
            public void run() {
                byte[] buf="hello android! ".getBytes();
                DatagramSocket sendSocket = null;
                try {
                    sendSocket = new DatagramSocket();
                    EditText editText = findViewById(R.id.edit);
                    String address = editText.getText().toString().trim();
                    InetAddress serverAddr = InetAddress.getByName(address);
                    DatagramPacket outPacket = new DatagramPacket(buf, buf.length,serverAddr, 52300);
                    sendSocket.send(outPacket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (sendSocket != null) {
                    sendSocket.close();
                }
            }
        });
    }
}

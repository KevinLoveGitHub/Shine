package com.shine.preview828;

import android.os.SystemClock;
import android.util.Log;
import android.util.SparseArray;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android_serialport_api.SerialPort;

/**
 * 摄像头操作者 云台控制摄像头的位置和缩放 亮度
 */
public class CameraOperator {
    private static final String TAG = CameraOperator.class.getSimpleName();
    private static final String PATH = "/dev/ttyS1";
    public static final int Left = 0;
    public static final int Right = Left + 1;
    public static final int Up = Left + 2;
    public static final int Down = Left + 3;
    public static final int Stop = Left + 4;
    //缩放
    public static final int ZoomIn = Left + 5;
    public static final int ZoomUp = Left + 6;
    public static final int ZoomStop = Left + 7;
    //亮度
    public static final int brightUp = Left + 8;
    public static final int brightDown = Left + 9;

    private SerialPort mSerialPort;
    private OutputStream mOutputStream;
    private InputStream inputStream;

    //波特率
    private static final int BAUDRATE = 9600;
    private SparseArray<byte[]> mOperations;
    private ExecutorService mExecutorService;
    //亮度
    private final int[] handlebrightUp = new int[]{(byte) 129, 1, 4, 13, 2, (byte) 255};
    private final int[] handlebrightDown = new int[]{(byte) 129, 1, 4, 13, 3, (byte) 255};


    public CameraOperator() {
        mOperations = new SparseArray<>();
        mOperations.put(Left, new byte[]{(byte) 129, 1, 6, 1, 3, 3, 1, 3, (byte) 255});
        mOperations.put(Right, new byte[]{(byte) 129, 1, 6, 1, 3, 3, 2, 3, (byte) 255});
        mOperations.put(Up, new byte[]{(byte) 129, 1, 6, 1, 3, 3, 3, 1, (byte) 255});
        mOperations.put(Down, new byte[]{(byte) 129, 1, 6, 1, 3, 3, 3, 2, (byte) 255});
        mOperations.put(Stop, new byte[]{(byte) 129, 1, 6, 1, 1, 1, 3, 3, (byte) 255});

        mOperations.put(ZoomIn, new byte[]{(byte) 129, 1, 4, 7, 32, (byte) 255});
        mOperations.put(ZoomUp, new byte[]{(byte) 129, 1, 4, 7, 48, (byte) 255});
        mOperations.put(ZoomStop, new byte[]{(byte) 129, 1, 4, 7, 0, (byte) 255});

        mExecutorService = Executors.newSingleThreadExecutor();
    }

    /**
     * @param operatorIndex 上下左右命令索引
     * @param stopIndex     停止命令索引  停止转动和缩放
     */
    public void handleInstruction(int operatorIndex, int stopIndex) {
        mExecutorService.execute(() -> {
            try {
                if (mOutputStream == null) {
                    initialize();
                }
                if (mOutputStream != null) {
                    mOutputStream.write(mOperations.get(operatorIndex));
                }
                //让操作执行400毫秒 比如向左 然后执行停止命令
                SystemClock.sleep(300);
                if (mOutputStream != null) {
                    mOutputStream.write(mOperations.get(stopIndex));
                }
            } catch (IOException e) {
                Log.d(TAG, e.toString());
            }
        });
    }


    public void receiveThread() {
        // 接收
        mExecutorService.execute(() -> {

            int size;
            try {
                byte[] buffer = new byte[1024];
                if (inputStream == null)
                    return;
                size = inputStream.read(buffer);
                if (size > 0) {
                    String recinfo = new String(buffer, 0,
                            size);
                    Log.i("test", "接收到串口信息:" + recinfo);
                    //                            sb = recinfo;
                    //                            handler.sendEmptyMessage(1);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        });
    }


    private void initialize() {
        File file = new File(PATH);
        if (!file.canRead() || !file.canWrite()) {
            RootCommand rootCommand = new RootCommand();
            rootCommand.executeCommands("chmod 666 " + file.getAbsolutePath());
            rootCommand.close();
        }
        Log.d(TAG, "can Read " + file.canRead() + "canWrite" + file.canWrite());
        if (file.canRead() && file.canWrite()) {
            mSerialPort = new SerialPort(file, BAUDRATE, 0);
            mOutputStream = mSerialPort.getOutputStream();
            inputStream = mSerialPort.getInputStream();
        } else {
            Log.e(TAG, "打开串口失败");
        }
    }

    //退出时关闭窗口和线程池
    public void exit() {
        if (mSerialPort != null) {
            mSerialPort.close();
        }
        mExecutorService.shutdownNow();
    }


}

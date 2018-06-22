package org.lovedev.codingcamera;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.hardware.usb.UsbDevice;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.serenegiant.usb.DeviceFilter;
import com.serenegiant.usb.USBMonitor;
import com.serenegiant.usb.UVCCamera;

import org.lovedev.util.ExecutorHelpers;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";
    private USBMonitor mUSBMonitor;
    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSurfaceView = (SurfaceView) findViewById(R.id.preview);
        // 获得 SurfaceHolder 对象
        mSurfaceHolder = mSurfaceView.getHolder();
        // 设置 Surface 格式
        // 参数： PixelFormat中定义的 int 值 ,详细参见 PixelFormat.java
        mSurfaceHolder.setFormat(PixelFormat.TRANSPARENT);
        // 添加 Surface 的 callback 接口
        mUSBMonitor = new USBMonitor(this, mOnDeviceConnectListener);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mUSBMonitor.register();
        openUSUCamera();
    }

    private void openUSUCamera() {
        final List<DeviceFilter> filter = DeviceFilter.getDeviceFilters(this, R.xml.device_filter);
        List<UsbDevice> deviceList = mUSBMonitor.getDeviceList(filter);
        if (deviceList.size() > 0) {
            mUSBMonitor.requestPermission(deviceList.get(0));
        } else {
            Log.e(TAG, "onCreate: no camera device");
        }
    }


    private UVCCamera mCamera;
    private final USBMonitor.OnDeviceConnectListener mOnDeviceConnectListener = new USBMonitor.OnDeviceConnectListener() {
        @Override
        public void onAttach(final UsbDevice device) {
            Log.d(TAG, "onAttach: ");
        }

        @Override
        public void onConnect(final UsbDevice device, final USBMonitor.UsbControlBlock ctrlBlock, final boolean createNew) {
            Log.d(TAG, "onConnect");
            if (mCamera != null) {mCamera.destroy();}
            mCamera = new UVCCamera();
            ExecutorHelpers.getNetworkIO().execute(new Runnable() {
                @Override
                public void run() {
                    mCamera.open(ctrlBlock);
                    try {
                        mCamera.setPreviewSize(320, 240, UVCCamera.FRAME_FORMAT_YUYV);
                        mSurfaceHolder.addCallback(mSurfaceCallback);
                    } catch (final IllegalArgumentException e) {
                        Log.e(TAG, "run: ", e);
                    }
                }
            });
        }

        @Override
        public void onDisconnect(UsbDevice device, USBMonitor.UsbControlBlock ctrlBlock) {
            if (mCamera != null) {
                mCamera.close();
            }
        }


        @Override
        public void onDetach(final UsbDevice device) {
            Log.d(TAG, "onDetach() called with: device = [" + device + "]");
        }

        @Override
        public void onCancel() {
        }
    };


    private boolean isPreview;
    private SurfaceHolder.Callback mSurfaceCallback = new SurfaceHolder.Callback() {

        /**
         *  在 Surface 首次创建时被立即调用：活得叫焦点时。一般在这里开启画图的线程
         * @param surfaceHolder 持有当前 Surface 的 SurfaceHolder 对象
         */
        @Override
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            // Camera,open() 默认返回的后置摄像头信息
            //打开硬件摄像头，这里导包得时候一定要注意是android.hardware.Camera
            //设置角度
            mCamera.setPreviewDisplay(surfaceHolder);//通过SurfaceView显示取景画面
            mCamera.startPreview();//开始预览
            isPreview = true;//设置是否预览参数为真
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        /**
         *  在 Surface 被销毁时立即调用：失去焦点时。一般在这里将画图的线程停止销毁
         * @param surfaceHolder 持有当前 Surface 的 SurfaceHolder 对象
         */
        @Override
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            if (mCamera != null) {
                if (isPreview) {//正在预览
                    mCamera.stopPreview();
                }
            }
        }
    };


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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUSBMonitor.unregister();
    }

    /**
     * 设置 摄像头的角度
     *
     * @param activity 上下文
     * @param cameraId 摄像头ID（假如手机有N个摄像头，cameraId 的值 就是 0 ~ N-1）
     * @param camera   摄像头对象
     */
    public void setCameraDisplayOrientation(Activity activity,
                                            int cameraId, UVCCamera camera) {

        //        Camera.CameraInfo info = new android.hardware.Camera.CameraInfo();
        //        //获取摄像头信息
        //        android.hardware.Camera.getCameraInfo(cameraId, info);
        //        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        //        //获取摄像头当前的角度
        //        int degrees = 0;
        //        switch (rotation) {
        //            case Surface.ROTATION_0:
        //                degrees = 0;
        //                break;
        //            case Surface.ROTATION_90:
        //                degrees = 90;
        //                break;
        //            case Surface.ROTATION_180:
        //                degrees = 180;
        //                break;
        //            case Surface.ROTATION_270:
        //                degrees = 270;
        //                break;
        //        }
        //
        //        int result;
        //        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
        //            //前置摄像头
        //            result = (info.orientation + degrees) % 360;
        //            result = (360 - result) % 360; // compensate the mirror
        //        } else {
        //            // back-facing  后置摄像头
        //            result = (info.orientation - degrees + 360) % 360;
        //        }
        //        camera.setDisplayOrientation(result);
    }
}

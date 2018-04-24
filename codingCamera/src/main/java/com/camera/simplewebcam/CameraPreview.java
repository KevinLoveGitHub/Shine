package com.camera.simplewebcam;

import android.graphics.Bitmap;

/**
 * @author Kevin
 * @data 2018/4/18
 */
public class CameraPreview {
    // JNI functions
    public native int prepareCamera(int videoid);
    public native int prepareCameraWithBase(int videoid, int camerabase);
    public native void processCamera();
    public native void stopCamera();
    public native void pixeltobmp(Bitmap bitmap);
    static {
        System.loadLibrary("ImageProc");
    }
}

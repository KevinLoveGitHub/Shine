
package com.mstar.tvframework;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.TextView;

public class MstarUIActivity extends BaseUIActivity {

    // load JNI library
    static {
        // System.loadLibrary("ogrekit_ui_jni");
        // preloadBlendFile("/data/data/com.android.mlauncher/files/MStarAnHome.blend");
    }

    private static final String TAG = "MstarUIActivity";

    private static final int EGL_CONTEXT_CLIENT_VERSION = 0x3098;

    private static final int EGL_RENDERABLE_TYPE = 0x3040;

    private static final int EGL_OPENGL_ES2_BIT = 0x0004;

    private static final int EGL_OPENGL_ES_BIT = 0x0001;

    private static final String travel_mode_name = "traveling-mode";

    private static final String travel_res_name = "traveling-res";

    private static final String travel_mem_format_name = "traveling-mem-format";

    private static final String travel_speed_name = "traveling-speed";

    private TextView fpsText = null;

    public static native boolean preloadBlendFile(String blendFile);

    // //////////////////////////////////////////////////////////////////////////
    // interface declare begin
    /**
     * Function called when the application wants to initialize the application.
     *
     * @return True if initialization was successful.
     */
    @Override
    public native boolean init(String blendFile, String configFile, String EtcPath);

    /**
     * Called when the application is exiting
     */
    @Override
    public native boolean cleanup();

    // @Override
    // public native void setOffsets(int x, int y);

    /**
     * Function called on key events
     *
     * @param action The action of this event.
     * @param unicodeChar The unicode character represented by the entered key
     *            char. If this is 0, further info will have to be extracted
     *            from the event object.
     * @param event The event object if further details are needed.
     * @return True if the event was handled.
     */
    @Override
    public native boolean keyEvent(int action, int unicodeChar, int keyCode, KeyEvent event);

    @Override
    public native boolean render(int drawWidth, int drawHeight, boolean forceRedraw);

    @Override
    public native boolean axisEvent(int coordType, int btnType, int action, float x, float y,
            MotionEvent event);

    @Override
    public native boolean runLuaScript(String Script);

    @Override
    public native boolean setImage(Bitmap image, String appName, String sceneName, String objName,
            int subEntityID, int techID, int passID, int stateID);

    @Override
    public native boolean setImage(Bitmap image, String appName, String sceneName, String objName,
            int subEntityID, int techID, int passID, int stateID, boolean formatFlag);

    @Override
    public native String queryCurrentScene();

    @Override
    public native String queryCurrentFocusObj(String SceneName);

    @Override
    public native String queryCurrentFocusApp(String SceneName, String ObjName);

    @Override
    public native int queryAnimationStatus(String SceneName);

    @Override
    public native void registerCallBackString(String JavaClassName);

    @Override
    public void callBackError(int errorCode, String message) {
        Log.v(TAG, "callBackError " + errorCode + " error msg" + message);
    }

    @Override
    public void callBackSceneChange(String OldSceneName, String NewSceneName) {
        Log.v(TAG, "callBackSceneChange OldSceneName" + OldSceneName + " NewSceneName"
                + NewSceneName);
    }

    @Override
    public void callBackBlendFileChange(String OldFileName, String NewFileName) {
        Log.v(TAG, "callBackBlendFileChange OldFileName" + OldFileName + " NewFileName"
                + NewFileName);
    }

    @Override
    public void callBackAxisEvent(int coordType, int btnType, int action, float x, float y) {
        Log.v(TAG, "callBackAxisEvent ");
    }

    @Override
    public void callBackKeyEvent(String sceneName, String objName, int action, int unicodeChar,
            int keyCode) {
        Log.v(TAG, "callBackKeyEvent ");
    }

    @Override
    public void callBackFocusChange(String SceneName, String OldObjName, String NewObjName) {
        Log.v(TAG, "callBackFocusChange ");
    }

    @Override
    public void callBackAnimDone(String SceneName, String OldObjName, String NewObjName) {
        Log.v(TAG, "callBackAnimDone ");
    }

    public native boolean registerAnimToListen(String SceneName, String OldObjName,
            String NewObjName);

    public native boolean unRegisterListenAnim(String SceneName, String OldObjName,
            String NewObjName);

    @Override
    public native void createDynamicTexture(int width, int height, int num_mips,
            String pixelFormat, String name, String group, int texType, int usage,
            boolean hwGammaCorrection, int fsaa, int fsaaHint);

    @Override
    public native void delDynamicTexture();

    @Override
    public native void enableUpdateDynamicTex(boolean bFlag);

    @Override
    public native boolean replacewithDynamicTex(String appName, String sceneName, String objName,
            int subEntityID, int techID, int passID, int stateID);

    @Override
    public native int queryVECaptureAttr(String attribute);

    @Override
    public native void setVECaptureAttr(String attribute, int value);

    @Override
    public native void startVECapture();

    @Override
    public native void endVECapture();

    // interface declare end
    // //////////////////////////////////////////////////////////////////////////

    public void StartVECapture() {
        Log.v(TAG, "StartVECapture");

        startVECapture();
        enableUpdateDynamicTex(true);

    }

    public void EndVECapture() {
        Log.v(TAG, "endVECapture");
        enableUpdateDynamicTex(false);
        endVECapture();

    }

}

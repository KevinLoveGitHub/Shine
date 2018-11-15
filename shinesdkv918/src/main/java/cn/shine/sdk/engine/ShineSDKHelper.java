package cn.shine.sdk.engine;

import android.content.Context;

import java.lang.reflect.Method;

import cn.shine.sdk.interfaces.IDaemonManager;
import cn.shine.sdk.interfaces.ITvPlayerManager;
import dalvik.system.DexClassLoader;

/**
 * @auther 宋疆疆
 * @date 2015/5/8.
 */
public class ShineSDKHelper {

    private static final String LIB_DIR = "/data/work/lib/";
    private static final String LIB_PATH = LIB_DIR + "ShineSDK.jar";
    private static ITvPlayerManager mTvPlayerManager;
    private static IDaemonManager mDaemonManager;

    public static ITvPlayerManager getTvPlayerManager(Context context) throws Exception {
        if (mTvPlayerManager == null) {
            DexClassLoader loader = new DexClassLoader(LIB_PATH, context.getFilesDir().getAbsolutePath(), null, context.getClassLoader());
            Class aClass = loader.loadClass("cn.shine.sdk.tv.TvPlayerManager");
            Method method = aClass.getMethod("getInstance");
            mTvPlayerManager = (ITvPlayerManager) method.invoke(null);
        }
        return mTvPlayerManager;
    }

    public static IDaemonManager getDaemonManager(Context context) throws Exception {
        if (mDaemonManager == null) {
            DexClassLoader loader = new DexClassLoader(LIB_PATH, context.getFilesDir().getAbsolutePath(), null, context.getClassLoader());
            Class aClass = loader.loadClass("cn.shine.sdk.daemon.DaemonManager");
            Method method = aClass.getMethod("getInstance");
            mDaemonManager = (IDaemonManager) method.invoke(null);
        }
        return mDaemonManager;
    }
}

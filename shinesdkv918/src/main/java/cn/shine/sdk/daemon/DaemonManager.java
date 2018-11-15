package cn.shine.sdk.daemon;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.shine.systemmanage.aidl.IShineSystemManage;

import cn.shine.sdk.interfaces.IDaemonManager;
import cn.shine.sdk.util.Logger;

/**
 * 此类提供一些与底层配置有关的接口
 * 
 * @author 宋疆疆
 * @date 2014年7月8日 下午1:30:08
 */
public class DaemonManager implements IDaemonManager{

	private static final boolean DEBUG = false;
	private static DaemonManager mInstance = new DaemonManager();
	private IShineSystemManage myService;
	private SystemManagerConnection systemManagerConnection;
	private Context mContext;

	public static DaemonManager getInstance() {
		return mInstance;
	}

	private DaemonManager() {
		super();
	}

	/**
	 * 初始化方法，注意此类和Activity的生命周期要挂钩，建议在onCreate方法调用init函数；并且调用此方法后，建议等待3秒后再调用其他方法
	 * ，否则可能报空指针异常
	 * 
	 * @author 宋疆疆
	 * @date 2014年7月8日 上午11:18:06
	 * @param context
	 * @return 返回true为初始化成功，false为初始化失败
	 */
	public boolean init(Context context) {
		onDestory();
		mContext = context;
		systemManagerConnection = new SystemManagerConnection();
		Intent service = new Intent("com.shine.systemmanage.aidl");
		return context.bindService(service, systemManagerConnection,
				Context.BIND_AUTO_CREATE);
	}

	/**
	 * 关机的方法
	 * 
	 * @author 宋疆疆
	 * @date 2014年7月8日 上午11:20:53
	 * @return 返回false的话，请尝试调用init方法,并等待3秒
	 */
	public boolean shutdown() {
		try {
			myService.Shutdown();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 传入一个时间字符串，来设置开机时间
	 * 
	 * @author 宋疆疆
	 * @date 2014年7月8日 上午11:25:44
	 * @param time
	 *            使用字符串来表示时间，格式必须严格遵守"2014-03-03 23:03:23"样式
	 * @return 返回false的话，请尝试调用init方法,并等待3秒
	 */
	public boolean setPowerWakeTime(String time) {
		try {
			myService.SetPowerWakeTime(time);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 建议在Activity的onDestory方法调用此方法
	 * 
	 * @author 宋疆疆
	 * @date 2014年7月8日 上午11:33:31
	 */
	public void onDestory() {
		if (mContext != null && systemManagerConnection != null) {
			mContext.unbindService(systemManagerConnection);
			systemManagerConnection = null;
			myService = null;
		}
	}

	private class SystemManagerConnection implements ServiceConnection {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			myService = IShineSystemManage.Stub.asInterface(service);
			if (null == myService) {
				Logger.e(DEBUG, "myService is null");
			} else {
				Logger.i(DEBUG, "myService is connect");
			}
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			systemManagerConnection = null;
		}

	}
}

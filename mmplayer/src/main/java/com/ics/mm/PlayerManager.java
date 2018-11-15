package com.ics.mm;

//~--- non-JDK imports --------------------------------------------------------

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;

import java.lang.ref.WeakReference;

//~--- JDK imports ------------------------------------------------------------

/**
 * Class description ：
 * 918 播放库
 *
 * @author 姜春雨
 * @date 15/04/16
 */
public class PlayerManager {

	static {
		try {
			System.loadLibrary("mm_player_jni");
			native_init();
		} catch (UnsatisfiedLinkError e) {
			System.err.println("Cannot load mm_player library:\n " + e.toString());
		}
	}

	public static final int CONNECT_SERVER = 801;//网络连接801
	public static final int DISCONNECT_SERVER = 800;//网络断开会发送800
	private long mNativeContext;           // accessed by native methods
	private int mPlayerManagerContext;    // accessed by native methods
	private static OnPlayerEventListener mOnEventListener;
	private EventHandler mEventHandler;
	private int mSurfaceTexture;
	private SurfaceHolder mSurfaceHolder;           // for surface overlay
	private Surface mSurface;                 // for surface overlay


	protected enum EVENT {EVENT_FROM_NATIVE,}

	public enum EnumMmInterfaceNotifyType {
		E_MM_INTERFACE_EXIT_OK,                             // playback ok, and exit to ap正常退出
		E_MM_INTERFACE_EXIT_ERROR,                          // playback  error, and exit to ap退出有问题
		E_MM_INTERFACE_SUFFICIENT_DATA,                     // when playing, data enough to continue play, and codec wil resume正常播放
		E_MM_INTERFACE_INSUFFICIENT_DATA,                   // when playing, run short of data, and codec wil pause数据不足
		E_MM_INTERFACE_START_PLAY,                          // player init ok, and start playing播放成功
		E_MM_INTERFACE_RENDERING_START,                      //这个代表他真正的开始显示
		E_MM_INTERFACE_NULL,                                // playback notify null
	}

	public enum EnumMmInterfaceOptionType {
		E_MM_INTERFACE_OPTION_NULL, E_MM_INTERFACE_OPTION_SET_STARTTIME, E_MM_INTERFACE_OPTION_SET_TOTAL_TIME,
		E_MM_INTERFACE_OPTION_RESET_BUF, E_MM_INTERFACE_OPTION_ENABLE_SEAMLESS, E_MM_INTERFACE_OPTION_CHANGE_PROGRAM,
	}

	public PlayerManager() {
		Looper looper;
		if ((looper = Looper.myLooper()) != null) {
			mEventHandler = new EventHandler(this, looper);
		} else if ((looper = Looper.getMainLooper()) != null) {
			mEventHandler = new EventHandler(this, looper);
		} else {
			mEventHandler = null;
		}

		native_setup(new WeakReference<PlayerManager>(this));
	}

	private void updateSurfaceScreenOn() {
		if (mSurfaceHolder != null) {
			mSurfaceHolder.setKeepScreenOn(true);
		}
	}

	public boolean setDisplay(SurfaceHolder sh) {
		mSurfaceHolder = sh;
		if (sh != null) {
			mSurface = sh.getSurface();
 		} else {
//			mSurface=null;
			return false;
		}
		init(mSurface);

        updateSurfaceScreenOn();
		return true;
	}

	private static native final void native_init();

	private native final void native_setup(Object msrv_this);

	private native final void native_finalize();

	public native final void showFriendlyFrame();

	//设置网络超时的时间
	public native final void setRecvTimeOut(int seconds);

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		native_finalize();
	}

	/**
	 * Register setOnAudioEventListener(OnAudioEventListener listener), your
	 * listener will be triggered when the events posted from native code.
	 *
	 * @param listener OnAudioEventListener
	 */
	public void setOnPlayerEventListener(OnPlayerEventListener listener) {
		mOnEventListener = listener;
	}

	//this postEventFromNative is 0 表示播放异常 需要退出，

	private static void postEventFromNative(Object srv_ref, int eCmd, int u32Param, int u32Info) {
		Log.i("info", " this postEventFromNative is " + eCmd);

		PlayerManager srv = (PlayerManager) ((WeakReference) srv_ref).get();

		if (srv == null) {
			return;
		}
		if (eCmd == CONNECT_SERVER) {//网络连接801
			if (mOnEventListener != null) mOnEventListener.onPlayInfo(eCmd);
		} else if (eCmd == DISCONNECT_SERVER) {
			if (mOnEventListener != null) mOnEventListener.onPlayInfo(eCmd);
//网络断开会发送800
		} else {
			EnumMmInterfaceNotifyType notify = EnumMmInterfaceNotifyType.values()[eCmd];

			if (srv.mEventHandler != null) {
				Message m = srv.mEventHandler.obtainMessage(EVENT.EVENT_FROM_NATIVE.ordinal(), notify);
				srv.mEventHandler.sendMessage(m);
			}
		}
		return;
	}

	public native int setContentSource(String str);

	public native int init(Surface msurface);

	public native int deinit();

	public native int play(String url);

	public native int play_dianduuri(String url);

	public native int stop();

	public native int pause();

	public native int resume();

	public native int seekTo(int msec);

	public int setOption(EnumMmInterfaceOptionType eOption, int arg1) {
		return setOption(eOption.ordinal(), arg1);
	}

	private native int setOption(int eOption, int arg1);

	public native int getDuration();

	public native int getPlayerTime();

	public native int setPlayMode(int speed);

	/**
	 * @version 1.0
	 */
	public interface OnPlayerEventListener {

		/**
		 */
		public boolean onPlayEvent(PlayerManager mgr, EnumMmInterfaceNotifyType notify);

		void onPlayInfo(int code);
	}

	/**
	 * Class description ：
	 *
	 * @author 姜春雨
	 * @date 15/04/16
	 */
	private class EventHandler extends Handler {
		private PlayerManager mMSrv;

		public EventHandler(PlayerManager srv, Looper looper) {
			super(looper);
			mMSrv = srv;
		}

		@Override
		public void handleMessage(Message msg) {

			// System.out.println("PlayerManager.java line 216 ,handleMessage");
			if (mMSrv.mNativeContext == 0) {
				return;
			}

			EVENT[] ev = EVENT.values();

			switch (ev[msg.what]) {
				case EVENT_FROM_NATIVE: {
					if (mOnEventListener != null) {
						mOnEventListener.onPlayEvent(mMSrv, (EnumMmInterfaceNotifyType) msg.obj);
					}
				}

				return;

				default:
					System.err.println("Unknown message type " + msg.what);

					return;
			}
		}
	}
}

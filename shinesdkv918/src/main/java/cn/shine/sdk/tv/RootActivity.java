package cn.shine.sdk.tv;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;

import com.mstar.android.tv.TvCommonManager;
import com.mstar.android.tv.TvPictureManager;
import com.mstar.android.tv.TvS3DManager;
import com.mstar.android.tvapi.common.TvManager;
import com.mstar.android.tvapi.common.vo.TvOsType.EnumInputSource;
import com.mstar.tvsettingservice.ChannelDesk;
import com.mstar.tvsettingservice.ChannelDesk.EN_TUNING_SCAN_TYPE;
import com.mstar.tvsettingservice.CommonDesk;
import com.mstar.tvsettingservice.CommonDesk.EnumDeskEvent;
import com.mstar.tvsettingservice.CommonDesk.EnumScreenMode;
import com.mstar.tvsettingservice.CommonDesk.EnumSignalProgSyncStatus;
import com.mstar.tvsettingservice.TvDeskProvider;
import com.mstar.tvsrvfunc.DataBaseDeskImpl;
import com.mstar.tvsrvfunc.DeskCiEventListener.EVENT;
import com.mstar.tvsrvfunc.DeskTimerEventListener;

import cn.shine.sdk.tv.enums.ErrorCode;
import cn.shine.sdk.tv.listener.OnErrorListener;
import cn.shine.sdk.util.Logger;

class RootActivity {

    protected TvDeskProviderWrapper tvManagerProvider;
    private MyHandler myHandler = null;
    private MyTvHandler myTvHandler = null;
    private MyCiHandler myCiHandler = null;
    private MyTimerHandler myTimerHandler = null;
    private boolean bSignalLock = true;
    protected boolean bRootActivityActive = false;
    public static boolean m_bScreenSaverStatus = false;
    private int m_ScreenModeStatus = -1;
    protected boolean bCmd_TvApkExit = false;
    private EnumInputSource preInputSource = EnumInputSource.E_INPUT_SOURCE_NONE;
    private static boolean bIsStartMsrv = false;
    // protected CommonSkin cm = null;
    // private PictureSkin pm = null;
    // private S3DSkin s3dm = null;
    // private AudioSkin am = null;
    // private PipSkin ps = null;
    private static int systemAutoTime = 0;
    private static final String TAG = "sjj";
    protected int caCurNotifyIdx;
    protected int caCurEvent;
    private OnErrorListener mListener;
    private Object lock = new Object();
    private Context mContext;

    public RootActivity(Context context) {
        mContext = context;
    }

    public void init(TvDeskProvider tdp) {
        preInputSource = EnumInputSource.E_INPUT_SOURCE_NONE;
        tvManagerProvider = (TvDeskProviderWrapper) tdp;
        //temp
        TvCommonManager.getInstance();
        TvPictureManager.getInstance();
        TvS3DManager.getInstance();
        TvManager.getInstance().getMhlManager();
        TvManager.getInstance().getCecManager();
        //end
        bIsStartMsrv = true;
        myHandler = new MyHandler();
        myTvHandler = new MyTvHandler();
        myCiHandler = new MyCiHandler();
        myTimerHandler = new MyTimerHandler();

        Intent intent_tv_start = new Intent("com.mstar.tv.ui.tvstart");
        mContext.sendBroadcast(intent_tv_start);
        updateTvSourceSignal();
    }

    public void setOnErrorListener(OnErrorListener listener) {
        synchronized (lock) {
            mListener = listener;
        }
    }

    private boolean isSourceDirty = false;

    // 初始化视频播放
    private void updateTvSourceSignal() {

        ChannelDesk cd = tvManagerProvider.getChannelManagerInstance();

        tvManagerProvider.getDataBaseManagerInstance().openDB();
        DataBaseDeskImpl.getDataBaseMgrInstance().syncDirtyDataOnResume();
        EnumInputSource curInputSource = EnumInputSource.E_INPUT_SOURCE_NONE;
        int o = curInputSource.ordinal();
        DataBaseDeskImpl db = DataBaseDeskImpl.getDataBaseMgrInstance();
        preInputSource = EnumInputSource.values()[db.queryCurInputSrc()];
        curInputSource = tvManagerProvider.getCommonManagerInstance()
                .GetCurrentInputSource();
        Logger.i("@@@________preInputSource is :" + preInputSource);
        Logger.i("@@@________curInputSource is :" + curInputSource);
        // 此句话不执行
        if (isSourceDirty) {
            // the source is changed ,get fresh data from DB
            DataBaseDeskImpl.getDataBaseMgrInstance().queryAllVideoPara(
                    curInputSource.ordinal());
            Logger.i("==curinputsource.ordinal==" + curInputSource.ordinal());
        }
        isSourceDirty = true;
        if (bIsStartMsrv == true) {
            Logger.d("Tvapp", " bIsStartMsrv  true ");
            tvManagerProvider.getCommonManagerInstance().startMsrv();
            tvManagerProvider.getDemoManagerInstance().forceThreadSleep(false);

            int swMode = tvManagerProvider.getSettingManagerInstance()
                    .GetChannelSWMode().ordinal();
            if (swMode == 1) {
                tvManagerProvider.getChannelManagerInstance()
                        .setChannelChangeFreezeMode(true);
            } else {
                tvManagerProvider.getChannelManagerInstance()
                        .setChannelChangeFreezeMode(false);
            }
            // ]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]

            bIsStartMsrv = false;
        }
        // =====================================================

        // 9999999999999999999999999999999999999
        if (tvManagerProvider != null)// this call everytime when other activity
        // // exit
        {
            Logger.d("Tvapp", " reset  myHandler ");
            Logger.d("Tvapp", " reset  myTvHandler ");
            Logger.d("Tvapp", " reset  myCiHandler ");
            Logger.d("Tvapp", " reset  myTimerHandler ");
            tvManagerProvider.getCommonManagerInstance().setHandler(myHandler,
                    0);
            tvManagerProvider.getCommonManagerInstance().setHandler(
                    myTvHandler, 1);
            tvManagerProvider.getCommonManagerInstance().setHandler(
                    myCiHandler, 2);
            tvManagerProvider.getCommonManagerInstance().setHandler(
                    myTimerHandler, 3);
            // tvManagerProvider.getCommonManagerInstance().setHandler(
            // caViewHolder.getCaHandler(), 4);
            curInputSource = EnumInputSource.values()[db.queryCurInputSrc()];// long.chen
        }
        // 99999999999999999999999999999999999999999999
        bRootActivityActive = true;

        // 如果无信号的情况下进入雪花状态====
        setScreenSaver(curInputSource);

        // 12222122222222222222222222222
        if (curInputSource == EnumInputSource.E_INPUT_SOURCE_DTV) {

            cd.setUserScanType(EN_TUNING_SCAN_TYPE.E_SCAN_DTV);
            Logger.i("我进入了Dtv信号专区啊==========1212121212121212121212211===========");
        } else {
            cd.setUserScanType(EN_TUNING_SCAN_TYPE.E_SCAN_ATV);
            Logger.i("我进入了Atv信号专区啊==========1212121212121212121212211===========");
        }
        // 1212121212121212222222222222222222222222222222
        Logger.i("@@@________OnResume Over_____________");
    }

    protected void onPause() {
        bRootActivityActive = false;
        tvManagerProvider.getDataBaseManagerInstance().closeDB();
        Settings.System.putInt(mContext.getContentResolver(),
                "com.mstar.tv.isFirstCreated", 0);
    }

    public void onDestroy() {
        if (systemAutoTime > 0) {
            CommonDesk cd = tvManagerProvider.getCommonManagerInstance();
            EnumInputSource curInputSource = cd.GetCurrentInputSource();
            if (curInputSource == EnumInputSource.E_INPUT_SOURCE_DTV) {
            }
        }

        if (tvManagerProvider == null) {
        }
        tvManagerProvider.getDemoManagerInstance().forceThreadSleep(true);
        tvManagerProvider.getCommonManagerInstance().releaseHandler(0);
        tvManagerProvider.getCommonManagerInstance().releaseHandler(1);
        tvManagerProvider.getCommonManagerInstance().releaseHandler(2);
        tvManagerProvider.getCommonManagerInstance().releaseHandler(3);
        tvManagerProvider.getCommonManagerInstance().releaseHandler(4);
        // cm.disconnect();
        // pm.disconnect();
        // am.disconnect();
        bRootActivityActive = false;
    }

    private void setScreenSaver(EnumInputSource curInputSource) {
        if (curInputSource == EnumInputSource.E_INPUT_SOURCE_NONE) {
            curInputSource = tvManagerProvider.getCommonManagerInstance()
                    .GetCurrentInputSource();
        }
        bSignalLock = tvManagerProvider.getCommonManagerInstance()
                .isSignalStable();
        if ((bCmd_TvApkExit == false) && (bSignalLock == false)) {
            if (curInputSource != EnumInputSource.E_INPUT_SOURCE_ATV) {
                sendErrorMessage(80);
            }
        }
        if ((bCmd_TvApkExit == false) && (m_bScreenSaverStatus == true)) {
            sendErrorMessage(m_ScreenModeStatus);
        }
    }

    private void signalLockHandle(Message msg) {
        Bundle b = null;
        int what = msg.what;
        CommonDesk cd = tvManagerProvider.getCommonManagerInstance();
        EnumInputSource curInputSource = cd.GetCurrentInputSource();
        b = msg.getData();
        String MsgSource = b.getString("MsgSource");
        // Logger.d("RootActivity", "MsgSource =" + MsgSource);
        if (what == EnumDeskEvent.EV_SIGNAL_LOCK.ordinal()) {
            bSignalLock = true;
        } else if (what == EnumDeskEvent.EV_SIGNAL_UNLOCK.ordinal()) {
            bSignalLock = false;
            if (curInputSource == EnumInputSource.E_INPUT_SOURCE_ATV) {
                bSignalLock = true;// came from idle app
                return;
            }
        }
        if (bCmd_TvApkExit == true)// for exit app dialog
        {
            return;
        }
        if (bRootActivityActive == false) {
            if (cd.getHandler(1) != null) {
                if (what == EnumDeskEvent.EV_SIGNAL_LOCK.ordinal()
                        && MsgSource != null
                        && MsgSource.equals("DeskTvEventListener")) {
                    if (curInputSource == EnumInputSource.E_INPUT_SOURCE_VGA
                            && m_ScreenModeStatus == CommonDesk.EnumSignalProgSyncStatus.E_SIGNALPROC_STABLE_UN_SUPPORT_MODE
                            .ordinal()) {
                        bSignalLock = false;
                        return;
                    }
                    cd.getHandler(1)
                            .sendEmptyMessage(CommonDesk.Cmd_SignalLock);
                }
                if (what == EnumDeskEvent.EV_SIGNAL_UNLOCK.ordinal()
                        && MsgSource != null
                        && MsgSource.equals("DeskTvEventListener")) {
                    cd.getHandler(1).sendEmptyMessage(
                            CommonDesk.Cmd_SignalUnLock);
                    // note by sjj 2014.7.23
                    // if (TVRootApp.isSourceDirty)// input source changed, show
                    // no
                    // // signal if signal unlock
                    // {
                    // Intent intent;
                    // intent = new Intent(RootActivity.this,
                    // NoSignalActivity.class);
                    // intent.putExtra("IntendId", 80);
                    // RootActivity.this.startActivity(intent);
                    // }
                }
            }
            return;
        }
        if (what == EnumDeskEvent.EV_SIGNAL_LOCK.ordinal() && MsgSource != null
                && MsgSource.equals("DeskTvEventListener")) {
            if (curInputSource == EnumInputSource.E_INPUT_SOURCE_VGA) {
                if (m_ScreenModeStatus == CommonDesk.EnumSignalProgSyncStatus.E_SIGNALPROC_STABLE_UN_SUPPORT_MODE
                        .ordinal()) {
                    bSignalLock = false;
                    sendErrorMessage(m_ScreenModeStatus);
                } else {
                    if (m_ScreenModeStatus == CommonDesk.EnumSignalProgSyncStatus.E_SIGNALPROC_AUTO_ADJUST
                            .ordinal()) {
                        bSignalLock = false;
                        sendErrorMessage(m_ScreenModeStatus);
                    } else {
                        // note by sjj 2014.7.23
                        // Intent intent = new Intent(
                        // "com.com.mstar.tvsetting.hotkey.intent.action.SourceInFoActivity");
                        // startActivity(intent);
                    }
                }
            }
        }
        if (what == EnumDeskEvent.EV_SIGNAL_UNLOCK.ordinal()
                && MsgSource != null && MsgSource.equals("DeskTvEventListener")) {
            sendErrorMessage(80);
        }
    }

    private void screenSaveModeHandle(Message msg) {
        CommonDesk cd = tvManagerProvider.getCommonManagerInstance();
        EnumInputSource curInputSource = cd.GetCurrentInputSource();
        m_ScreenModeStatus = msg.getData().getInt("Status", -1);
        int status = m_ScreenModeStatus;
        switch (curInputSource) {
            case E_INPUT_SOURCE_ATV:
                break;
            case E_INPUT_SOURCE_DTV:
                if (m_ScreenModeStatus == CommonDesk.EnumScreenMode.MSRV_DTV_SS_COMMON_VIDEO
                        .ordinal()) {
                    m_bScreenSaverStatus = false;
                } else if (m_ScreenModeStatus == CommonDesk.EnumScreenMode.MSRV_DTV_SS_CI_PLUS_AUTHENTICATION
                        .ordinal()) {
                    m_bScreenSaverStatus = false;
                } else {
                    m_bScreenSaverStatus = true;
                }
                if (bCmd_TvApkExit == true)// for exit app dialog
                {
                    return;
                }
                if (bRootActivityActive == false) {
                    if ((m_bScreenSaverStatus == false)
                            && (cd.getHandler(1) != null)) {
                        cd.getHandler(1).sendEmptyMessage(
                                CommonDesk.Cmd_CommonVedio);
                    } else {
                        // note by sjj 2014.7.23
                        // if (TVRootApp.isSourceDirty) {
                        // Intent intent;
                        // intent = new Intent(RootActivity.this,
                        // NoSignalActivity.class);
                        // intent.putExtra("IntendId", m_ScreenModeStatus);
                        // RootActivity.this.startActivity(intent);
                        // }
                    }
                    return;
                }
                if (m_bScreenSaverStatus && bCmd_TvApkExit == false) {
                    sendErrorMessage(m_ScreenModeStatus);
                }
                break;
            case E_INPUT_SOURCE_VGA: {
                if (status == CommonDesk.EnumSignalProgSyncStatus.E_SIGNALPROC_STABLE_UN_SUPPORT_MODE
                        .ordinal()) {
                    m_bScreenSaverStatus = true;
                    sendErrorMessage(status);
                } else {
                    if (status == CommonDesk.EnumSignalProgSyncStatus.E_SIGNALPROC_AUTO_ADJUST
                            .ordinal()) {
                        m_bScreenSaverStatus = true;
                        sendErrorMessage(status);
                    } else {
                        // Intent intent = new Intent(
                        // "com.com.mstar.tvsetting.hotkey.intent.action.SourceInFoActivity");
                        // startActivity(intent);
                    }

                }
            }
            break;
            case E_INPUT_SOURCE_YPBPR:
            case E_INPUT_SOURCE_YPBPR2:
            case E_INPUT_SOURCE_YPBPR3: {
            }
            break;
            case E_INPUT_SOURCE_HDMI:
            case E_INPUT_SOURCE_HDMI2:
            case E_INPUT_SOURCE_HDMI3:
            case E_INPUT_SOURCE_HDMI4: {
                if (status == CommonDesk.EnumSignalProgSyncStatus.E_SIGNALPROC_UNSTABLE
                        .ordinal()) {
                    sendErrorMessage(status);
                }
            }
            break;
        }
    }

    private void tvApkExitHandler(Message msg) {
        bRootActivityActive = false;
        bCmd_TvApkExit = true;
        return;
    }

    private boolean checkmsg(int what) {
        if (what > CommonDesk.Cmd_XXX_Min && what < CommonDesk.Cmd_XXX_Max) {
            Logger.d("TvApp", "get xxx msg:" + what);
            return true;
        } else if (what > EnumDeskEvent.EV_HBBTV_UI_EVENT.ordinal()
                || what < EnumDeskEvent.EV_DTV_CHANNELNAME_READY.ordinal()) {
            return false;
        } else {
            return true;
        }
    }

    class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            Logger.d("MyHandler msg what: " + what);
            if (checkmsg(what) == false) {
                return;
            }
            if (what == EnumDeskEvent.EV_SIGNAL_LOCK.ordinal()
                    || what == EnumDeskEvent.EV_SIGNAL_UNLOCK.ordinal()) {
                signalLockHandle(msg);
            } else if (what == EnumDeskEvent.EV_SCREEN_SAVER_MODE.ordinal()) {
                screenSaveModeHandle(msg);
            } else if (what == CommonDesk.Cmd_TvApkExit) {
                tvApkExitHandler(msg);
            }
            super.handleMessage(msg);
        }
    }

    class MyTvHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            Logger.d("MyTvHandler msg what: " + what);
            if (what == 65) {
                onError(ErrorCode.GET_SINGLE);
            }
            if (what == EnumDeskEvent.EV_DTV_AUTO_UPDATE_SCAN.ordinal()) {
                // openAutoUpdateScanDialog();
            } else if (what == EnumDeskEvent.EV_TS_CHANGE.ordinal()) {
            } else if (what == EnumDeskEvent.EV_MHEG5_STATUS_MODE.ordinal()) {
                // @todo: need review, current RootActivity/SourceInfoActivity
                // will monitor the mheg5 status mode event
                if (msg.getData().getInt("Mheg5StatusMode") != 0) {
                    // Intent intent = new Intent(RootActivity.this,
                    // Mheg5Activity.class);
                    // RootActivity.this.startActivity(intent);
                }
                Logger.i("get mheg5 event EV_MHEG5_STATUS_MODE");
            }
        }

        public void openAutoUpdateScanDialog() {
            // Intent intent = new Intent(RootActivity.this,
            // AutoUpdateScanDialogActivity.class);
            // RootActivity.this.startActivity(intent);
        }
    }

    class MyCiHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            Logger.d("MyCiHandler msg what: " + what);
            if (what == EVENT.EV_CIMMI_UI_DATA_READY.ordinal()) {
                goToCimmi();
            } else if (what == EVENT.EV_CIMMI_UI_CLOSEMMI.ordinal()) {
                // finish();
            } else if (what == EVENT.EV_CIMMI_UI_CARD_INSERTED.ordinal()) {
                // Toast toast = Toast.makeText(RootActivity.this,
                // R.string.str_cimmi_hint_ci_inserted, 5);
                // toast.show();
            } else if (what == EVENT.EV_CIMMI_UI_CARD_REMOVED.ordinal()) {
                // Toast toast = Toast.makeText(RootActivity.this,
                // R.string.str_cimmi_hint_ci_removed, 5);
                // toast.show();
            }
            super.handleMessage(msg);
        }

        public boolean goToDtvAutoUpdateScan() {
            return true;
        }

        public boolean goToCimmi() {
            Logger.i("no single");
            return true;
        }
    }

    class MyTimerHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            Logger.d("MyCiHandler msg what: " + what);
            if (what == DeskTimerEventListener.EVENT.EV_DESTROY_COUNTDOWN
                    .ordinal()) {
            } else if (what == DeskTimerEventListener.EVENT.EV_ONESECOND_BEAT
                    .ordinal()) {
            } else if (what == DeskTimerEventListener.EVENT.EV_LASTMINUTE_WARN
                    .ordinal()) {
            } else if (what == DeskTimerEventListener.EVENT.EV_UPDATE_LASTMINUTE
                    .ordinal()) {
            } else if (what == DeskTimerEventListener.EVENT.EV_SIGNAL_LOCK
                    .ordinal()) {
            } else if (what == DeskTimerEventListener.EVENT.EV_EPG_TIME_UP
                    .ordinal()) {
            } else if (what == DeskTimerEventListener.EVENT.EV_EPGTIMER_COUNTDOWN
                    .ordinal()) {
                goToCountDownTimerPage();
            } else if (what == DeskTimerEventListener.EVENT.EV_EPGTIMER_RECORD_START
                    .ordinal()) {
            } else if (what == DeskTimerEventListener.EVENT.EV_PVR_NOTIFY_RECORD_STOP
                    .ordinal()) {
            } else if (what == DeskTimerEventListener.EVENT.EV_OAD_TIMESCAN
                    .ordinal()) {
            }
            super.handleMessage(msg);
        }

        public boolean goToCountDownTimerPage() {
            Logger.i("no single");
            return true;
        }
    }

    Thread t_atv = null;
    Thread t_dtv = null;

    private void sendErrorMessage(int error) {
        if (mListener == null) {
            return;
        }
        TvDeskProvider serviceProvider = tvManagerProvider;
        CommonDesk cd = serviceProvider.getCommonManagerInstance();
        EnumInputSource curInputSource = cd.GetCurrentInputSource();
        if (error == 80) {
            onError(ErrorCode.NO_SIGNAL);
        } else if (tvManagerProvider.getCommonManagerInstance()
                .GetCurrentInputSource() == EnumInputSource.E_INPUT_SOURCE_VGA) {
            if (error == EnumSignalProgSyncStatus.E_SIGNALPROC_STABLE_UN_SUPPORT_MODE
                    .ordinal()) {
                onError(ErrorCode.UNSUPPORTED);
            } else {
                if (error == EnumSignalProgSyncStatus.E_SIGNALPROC_AUTO_ADJUST
                        .ordinal()) {
                    onError(ErrorCode.AUTO_ADJUST);
                    myHandler.sendEmptyMessageDelayed(
                            CommonDesk.Cmd_CommonVedio, 3000);
                } else {
                    onError(ErrorCode.UNKOWN);
                }
            }
        } else if ((curInputSource == EnumInputSource.E_INPUT_SOURCE_HDMI
                || curInputSource == EnumInputSource.E_INPUT_SOURCE_HDMI2
                || curInputSource == EnumInputSource.E_INPUT_SOURCE_HDMI3 || curInputSource == EnumInputSource.E_INPUT_SOURCE_HDMI4)
                && error == EnumSignalProgSyncStatus.E_SIGNALPROC_UNSTABLE
                .ordinal()) {
            onError(ErrorCode.UNSUPPORTED);
        } else {
            if (curInputSource == EnumInputSource.E_INPUT_SOURCE_DTV) {
                if (error == EnumScreenMode.MSRV_DTV_SS_INVALID_SERVICE
                        .ordinal()) {
                    onError(ErrorCode.INVALID_SERVICE);
                } else if (error == EnumScreenMode.MSRV_DTV_SS_NO_CI_MODULE
                        .ordinal()) {
                    onError(ErrorCode.NO_CI_MODULE);
                } else if (error == EnumScreenMode.MSRV_DTV_SS_CI_PLUS_AUTHENTICATION
                        .ordinal()) {
                    onError(ErrorCode.CI_PLUS_AUTHENTICATION);
                } else if (error == EnumScreenMode.MSRV_DTV_SS_SCRAMBLED_PROGRAM
                        .ordinal()) {
                    onError(ErrorCode.SCRAMBLED_PROGRAM);
                } else if (error == EnumScreenMode.MSRV_DTV_SS_CH_BLOCK
                        .ordinal()) {
                    onError(ErrorCode.PROGRAM_BLOCK);
                } else if (error == EnumScreenMode.MSRV_DTV_SS_PARENTAL_BLOCK
                        .ordinal()) {
                    onError(ErrorCode.PARENTAL_BLOCK);
                } else if (error == EnumScreenMode.MSRV_DTV_SS_AUDIO_ONLY
                        .ordinal()) {
                    onError(ErrorCode.AUDIO_ONLY);
                } else if (error == EnumScreenMode.MSRV_DTV_SS_DATA_ONLY
                        .ordinal()) {
                    onError(ErrorCode.DATA_ONLY);
                } else if (error == EnumScreenMode.MSRV_DTV_SS_COMMON_VIDEO
                        .ordinal()) {
                    onError(ErrorCode.UNKOWN);
                } else if (error == EnumScreenMode.MSRV_DTV_SS_INVALID_PMT
                        .ordinal()) {
                    onError(ErrorCode.INVALID_PMT);
                } else if (error == EnumScreenMode.MSRV_DTV_SS_CA_NOTIFY
                        .ordinal()) {
                    // note by sjj 2014.7.23
                    // if ((getIntent() != null)
                    // && (getIntent().getExtras() != null)) {
                    // caEventType = getIntent().getIntExtra("caEventType", 0);
                    // caMsgType = getIntent().getIntExtra("caMsgType", 0);
                    // resId = getIntent().getIntExtra("resId", 0);
                    // }
                    // if (caMsgType == 0) {
                    // Intent intent = new Intent(NoSignalActivity.this,
                    // RootActivity.class);
                    // NoSignalActivity.this.startActivity(intent);
                    // finish();
                    // } else {
                    // title.setText(resId);
                    // }
                }
            } else if (curInputSource == EnumInputSource.E_INPUT_SOURCE_ATV) {
                if (error == EnumScreenMode.MSRV_DTV_SS_COMMON_VIDEO.ordinal()) {
                    onError(ErrorCode.UNKOWN);
                }
            }
        }
    }

    private void onError(ErrorCode error) {
        synchronized (lock) {
            if (mListener != null) {
                mListener.onError(error);
            }
        }
    }

}

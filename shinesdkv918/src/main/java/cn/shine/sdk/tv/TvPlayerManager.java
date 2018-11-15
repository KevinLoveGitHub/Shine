package cn.shine.sdk.tv;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import com.mstar.android.tvapi.common.AudioManager;
import com.mstar.android.tvapi.common.TvManager;
import com.mstar.android.tvapi.common.vo.EnumAudioVolumeSourceType;
import com.mstar.android.tvapi.common.vo.EnumProgramCountType;
import com.mstar.android.tvapi.common.vo.EnumScalerWindow;
import com.mstar.android.tvapi.common.vo.TvOsType;
import com.mstar.android.tvapi.common.vo.VideoWindowType;
import com.mstar.tvsettingservice.ChannelDesk;
import com.mstar.tvsettingservice.CommonDesk;
import com.mstar.tvsettingservice.DataBaseDesk;
import com.mstar.tvsettingservice.DtvInterface;
import com.mstar.tvsettingservice.DtvInterface.MEMBER_SERVICETYPE;
import com.mstar.tvsettingservice.TvDeskProvider;
import com.mstar.tvsrvfunc.DataBaseDeskImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import cn.shine.sdk.interfaces.ITvPlayerManager;
import cn.shine.sdk.tv.bean.ProgramInfo;
import cn.shine.sdk.tv.enums.EN_ANTENNA_TYPE;
import cn.shine.sdk.tv.enums.EN_TUNING_SCAN_TYPE;
import cn.shine.sdk.tv.enums.EnumInputSource;
import cn.shine.sdk.tv.enums.MEMBER_COUNTRY;
import cn.shine.sdk.tv.listener.OnErrorListener;
import cn.shine.sdk.tv.listener.ScanListener;
import cn.shine.sdk.util.Logger;

/**
 * 控制电视的上层封装类，提供了一些方法进行电视播放
 *
 * @author 宋疆疆
 * @date 2014-4-15 下午5:05:02
 */
public class TvPlayerManager implements ITvPlayerManager {

    private static final boolean DEBUG = true;
    private static TvPlayerManager mInstance = new TvPlayerManager();
    private ChannelDesk cd;
    private DataBaseDeskImpl db;
    private Context mContext;
    private SurfaceView mSurfaceView;
    private DisplayMetrics outMetrics;
    private SurfaceHolder.Callback mCallback;
    private TvDeskProvider tvDeskProvider;
    private int panelWidth;
    private int panelHeight;
    private CommonDesk mCommondesk;
    private Channeltuning mChanneltuning;
    private EnumAudioVolumeSourceType volume_type = EnumAudioVolumeSourceType.E_VOL_SOURCE_SPEAKER_OUT;
    private RootActivity ra;

    /**
     * 获取当前的信号源类型
     *
     * @return
     * @author 宋疆疆
     * @date 2014年7月2日 下午2:26:18
     */
    public EnumInputSource getCurSource() {
        return EnumInputSource.values()[mCommondesk.GetCurrentInputSource()
                .ordinal()];
    }

    public static TvPlayerManager getInstance() {
        return mInstance;
    }

    private TvPlayerManager() {
        super();
    }

    /**
     * 你应该在程序的一开始调用此方法，并且只需要调用一次即可； 只有调用此方法后 ，其他方法才能保证正常运行
     *
     * @param context
     * @author 宋疆疆
     * @date 2014-4-15 下午3:37:17
     */
    public void init(Context context) {
        mContext = context;

        tvDeskProvider = new TvDeskProviderWrapper(context);

        cd = tvDeskProvider.getChannelManagerInstance();

        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        // Log.i(TAG, "width： " + outMetrics.widthPixels + ", height: "
        // + outMetrics.heightPixels);

        mCommondesk = tvDeskProvider.getCommonManagerInstance();

        getResolution();
    }

    /**
     * 调用完init方法后，即可调用此方法开启电视功能； 需要注意，调用完init，建议等待2秒再调用此方法
     *
     * @author 宋疆疆
     * @date 2014-4-15 下午3:38:20
     */
    public void startTvPlayer() {
        if (ra != null) {
            ra.onPause();
            ra.onDestroy();
            ra = null;
        }
        ra = new RootActivity(mContext);
        ra.init(tvDeskProvider);
    }

    /**
     * 设置监听切换频道时，频道的报错信息的接口；<br>
     * 注意1：此方法只能在调用完startTvPlayer()后使用；<br>
     * 注意2：changeSource() 会清空listener，所以要再次调用此方法
     *
     * @param listener
     * @author 宋疆疆
     * @date 2014年7月23日 上午10:59:47
     */
    public void setOnErrorListener(OnErrorListener listener) {
        if (ra != null) {
            ra.setOnErrorListener(listener);
        }
    }

    /**
     * 改变切换频道时的切换效果；此方法只能在调用startTvPlayer()方法之后再调用才起作用，并且切换过信号源后，需要重新调用此方法。
     *
     * @param freezeMode 如果为true，切换效果为镜像模式；如果为false，切换效果为黑屏效果
     * @author 宋疆疆
     * @date 2014年7月2日 下午2:27:37
     */
    public void setChannelChangeFreezeMode(boolean freezeMode) {
        cd.setChannelChangeFreezeMode(freezeMode);
    }

    /**
     * 获取对应信号源的所有频道的信息；注意只有在天线类型设置为正确的类型后，才能成功获取频道信息
     *
     * @param source 只支持ATV和DTV
     * @return
     * @author 宋疆疆
     * @date 2014-4-15 下午4:20:45
     */
    public List<ProgramInfo> getProgramList(EnumInputSource source) {
        EnumProgramCountType type = null;
        int serviceType = -1;
        if (source == EnumInputSource.E_INPUT_SOURCE_ATV) {
            type = EnumProgramCountType.E_COUNT_ATV;
            serviceType = 0;
        } else if (source == EnumInputSource.E_INPUT_SOURCE_DTV) {
            type = EnumProgramCountType.E_COUNT_DTV;
            serviceType = 1;
        } else {
            Logger.w("getProgramList() source is not atv or dtv, return null");
            return null;
        }
        List<ProgramInfo> list = new ArrayList<ProgramInfo>();
        int m_nServiceNum = cd.getProgramCount(EnumProgramCountType.values()[type.ordinal()]);
        Logger.d(DEBUG, "ProgramListViewActivity=总共台数：==" + m_nServiceNum);
        for (int k = 0; k < m_nServiceNum; k++) {
            ProgramInfo pgi = getProgramInfo(cd.getProgramInfoByIndex(k));
            Logger.v(DEBUG, "serviceType: " + pgi.serviceType + ", sourceType: " + source);
            if (pgi.serviceType == serviceType) {
                list.add(pgi);
                Logger.d(DEBUG, pgi.frequency + "=(1)frequency\n=="
                        + pgi.favorite + "=(2)favorite\n=="
                        + pgi.transportStreamId + "=(3)transportStreamId\n=="
                        + pgi.serviceType + "=(4)serviceType\n=="
                        + pgi.serviceName + "=(5)serviceName\n=="
                        + pgi.serviceId + "=(6)serviceId\n=="
                        + pgi.screenMuteStatus + "=(7)screenMuteStatus\n=="
                        + pgi.queryIndex + "=(8)queryIndex\n==" + pgi.progId
                        + "=(9)progId\n==" + pgi.number + "=(10)number\n=="
                        + pgi.minorNum + "=(11)minorNum\n==" + pgi.majorNum
                        + "=(12)majorNum\n==" + pgi.antennaType
                        + "=(13)antennaType\n==");
            }
        }
        return list;
    }

    private ProgramInfo getProgramInfo(com.mstar.android.tvapi.common.vo.ProgramInfo info) {
        return new ProgramInfo(info.queryIndex, info.number, info.majorNum, info.minorNum, info.progId, info.favorite, info.isLock, info.isSkip, info.isScramble, info.isDelete, info.isVisible, info.isHide, info.serviceType, info.screenMuteStatus, info.serviceName, info.frequency, info.transportStreamId, info.serviceId, info.antennaType);
    }

    /**
     * 获取当前频道的信息； 需要注意的是，DTV换台以后，需要延迟1秒才能获取当前频道信息
     *
     * @return
     * @author 宋疆疆
     * @date 2014-4-15 下午4:27:08
     */
    public ProgramInfo getCurrentProgramInfo() {
        return getProgramInfo(cd.getCurrProgramInfo());
    }

    /**
     * 改变频道号，想换台就调用这个方法
     *
     * @param number 目标频道号，比如通过ProgramInfo.number获取的频道号
     * @author 宋疆疆
     * @date 2014-4-15 下午5:32:55
     */
    public boolean changeChannel(int number) {
        MEMBER_SERVICETYPE type = null;
        if (getCurSource() == EnumInputSource.E_INPUT_SOURCE_ATV) {
            type = MEMBER_SERVICETYPE.E_SERVICETYPE_ATV;
        } else if (getCurSource() == EnumInputSource.E_INPUT_SOURCE_DTV) {
            type = MEMBER_SERVICETYPE.E_SERVICETYPE_DTV;
        } else {
            return false;
        }
        return cd.programSel(number, type);
    }

    /**
     * 改变信号源，比如从ATV切换到DTV
     *
     * @param sourcType 支持ATV、DTV、VGA、HDMI四种信号源
     * @author 宋疆疆
     * @date 2014-4-15 下午5:36:28
     */
    public void changeSource(EnumInputSource sourcType) {
        if (getCurSource() == sourcType) {
            return;
        }
        setOnErrorListener(null);
        tvDeskProvider.getCommonManagerInstance().SetInputSource(
                TvOsType.EnumInputSource.values()[sourcType.ordinal()]);
    }

    /**
     * 改变天线类型
     *
     * @param antennaType
     * @author 宋疆疆
     * @date 2014年7月11日 下午3:57:23
     */
    public void changeAntennaType(EN_ANTENNA_TYPE antennaType) {
        cd.dtvSetAntennaType(DtvInterface.EN_ANTENNA_TYPE.values()[antennaType
                .ordinal()]);
    }

    /**
     * 设置供电视显示用的view
     *
     * @param surfaceView
     * @author 宋疆疆
     * @date 2014-4-16 下午2:35:39
     */
    public void setSurfaceView(SurfaceView surfaceView) {
        mSurfaceView = surfaceView;
        mSurfaceView.getHolder().setType(
                SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        mCallback = new SurfaceHolder.Callback() {
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                Logger.v(DEBUG, "===surfaceDestroyed===");
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    Logger.v(DEBUG, "===surfaceCreated===");
                    TvManager.getInstance().getPlayerManager().setDisplay(holder);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {
                Logger.v(DEBUG, "===surfaceChanged===");
            }

        };
        mSurfaceView.getHolder().addCallback(
                (SurfaceHolder.Callback) mCallback);
    }

    /**
     * 设置电视的播放区域
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @author 宋疆疆
     * @date 2014-4-16 上午9:24:46
     */
    public void setTVSize(int x, int y, int width, int height) {
        Logger.d(DEBUG, "x: " + x + ", y: " + y + ", width: " + width + ", height: " + height);
        try {
            VideoWindowType videoWindowType = new VideoWindowType();
            int dx = (int) ((float) panelWidth / (float) outMetrics.widthPixels * (float) x);
            int dy = (int) ((float) panelHeight
                    / (float) outMetrics.heightPixels * (float) y);
            int dWidth = (int) ((float) panelWidth
                    / (float) outMetrics.widthPixels * (float) width);
            int dHeight = (int) ((float) panelHeight
                    / (float) outMetrics.heightPixels * (float) height);
            // videoWindowType.x = 0;
            // videoWindowType.y = 0;
            // videoWindowType.width = 1280;
            // videoWindowType.height = 720;
            videoWindowType.x = dx;
            videoWindowType.y = dy;
            videoWindowType.width = dWidth;
            videoWindowType.height = dHeight;
            Logger.i(DEBUG, "dx: " + dx + ", dy: " + dy + ", dWidth: " + dWidth + ", dHeight: " + dHeight);
            TvManager.getInstance().getPictureManager().selectWindow(
                    EnumScalerWindow.E_MAIN_WINDOW);
            TvManager.getInstance().getPictureManager().setDisplayWindow(videoWindowType);
            TvManager.getInstance().getPictureManager().scaleWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getResolution() {
        String rootDir = "";
        String path = "/config/model/Customer_1.ini";
        if (Build.VERSION.SDK_INT == 18) {
            rootDir = "/tvconfig";
        } else {
            rootDir = "/tvservice";
        }
        File file = new File(path);
        String configPath = "not find path";
        panelWidth = -1;
        panelHeight = -1;
        Reader in = null;
        BufferedReader br = null;
        try {
            in = new FileReader(file);
            br = new BufferedReader(in);
            String line = "";
            while ((line = br.readLine()) != null) {
                if (line.toLowerCase().startsWith("m_ppanelname")) {
                    String[] split = line.split("=");
                    // configPath = split[1].trim().replace("\"", "")
                    // .replace(";", "");
                    configPath = split[1].trim();
                    configPath = configPath.substring(configPath.indexOf("/"), configPath.indexOf(".ini") + 4);
                    break;
                }
            }
            file = new File(rootDir + configPath);
            in = new FileReader(file);
            br = new BufferedReader(in);
            while ((line = br.readLine()) != null) {
                if (panelWidth != -1 && panelHeight != -1) {
                    break;
                }
                if (line.toLowerCase().startsWith("m_wpanelwidth")) {
                    String[] split = line.split("=");
                    String[] w = split[1].trim().split(";");
                    panelWidth = Integer.parseInt(w[0].trim());
                }
                if (line.toLowerCase().startsWith("m_wpanelheight")) {
                    String[] split = line.split("=");
                    String[] h = split[1].trim().split(";");
                    panelHeight = Integer.parseInt(h[0].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 在不需要电视播放的时候，可以调用此方法，比如onPause方法里调用
     *
     * @author 宋疆疆
     * @date 2014-4-16 上午9:25:40
     */
    public void releaseSurfaceView() {
        if (mSurfaceView != null) {
            mSurfaceView.getHolder().removeCallback(
                    (SurfaceHolder.Callback) mCallback);
            mSurfaceView = null;
        }
    }

    /**
     * 设置音量大小
     *
     * @param volume
     * @author 宋疆疆
     * @date 2014年7月10日 下午3:48:40
     */
    private boolean setSound(byte volume) {
        AudioManager am = TvManager.getInstance().getAudioManager();
        try {
            am.setAudioVolume(volume_type, volume);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private int getVolume() {
        AudioManager am = TvManager.getInstance().getAudioManager();
        byte audioVolume = -1;
        try {
            audioVolume = am.getAudioVolume(volume_type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return audioVolume;
    }

    /**
     * 开始搜台的方法；注意一旦开始搜台，底层就会搜索到结束为止，如果想中途停止搜索，必须要调用stopTune()方法
     *
     * @param country
     * @author 宋疆疆
     * @date 2014年7月10日 下午4:35:10
     */
    public void startTune(MEMBER_COUNTRY country, EN_TUNING_SCAN_TYPE scantype,
                          ScanListener listener) {
        cd.setSystemCountry(DataBaseDesk.MEMBER_COUNTRY.values()[country
                .ordinal()]);
        cd.setUserScanType(ChannelDesk.EN_TUNING_SCAN_TYPE.values()[scantype
                .ordinal()]);
        mChanneltuning = new Channeltuning();
        mChanneltuning.start(mContext, null, false, listener,
                ChannelDesk.EN_TUNING_SCAN_TYPE.values()[scantype.ordinal()]);
    }

    /**
     * 停止搜台的方法
     *
     * @author 宋疆疆
     * @date 2014年7月14日 下午2:36:39
     */
    public void stopTune() {
        if (mChanneltuning != null) {
            mChanneltuning.stop();
            mChanneltuning.stop();
        }
    }

    /**
     * 在程序退出时，你应该要调用此方法
     *
     * @author 宋疆疆
     * @date 2014-4-16 上午9:27:15
     */
    public void onDestroy() {
        releaseSurfaceView();
        // setTVSize(0, 0, 1280, 720);
        setTVSize(0, 0, panelWidth, panelHeight);
        if (ra != null) {
            ra.onPause();
            ra.onDestroy();
            ra = null;
        }
    }

}

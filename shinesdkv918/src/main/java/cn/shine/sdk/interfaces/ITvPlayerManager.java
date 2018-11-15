package cn.shine.sdk.interfaces;

import android.content.Context;
import android.view.SurfaceView;

import java.util.List;

import cn.shine.sdk.tv.bean.ProgramInfo;
import cn.shine.sdk.tv.enums.EN_ANTENNA_TYPE;
import cn.shine.sdk.tv.enums.EN_TUNING_SCAN_TYPE;
import cn.shine.sdk.tv.enums.EnumInputSource;
import cn.shine.sdk.tv.enums.MEMBER_COUNTRY;
import cn.shine.sdk.tv.listener.OnErrorListener;
import cn.shine.sdk.tv.listener.ScanListener;

/**
 * @auther 宋疆疆
 * @date 2015/5/8.
 */
public interface ITvPlayerManager {

    /**
     * 获取当前的信号源类型
     *
     * @return
     * @author 宋疆疆
     * @date 2014年7月2日 下午2:26:18
     */
    public EnumInputSource getCurSource();

    /**
     * 你应该在程序的一开始调用此方法，并且只需要调用一次即可； 只有调用此方法后 ，其他方法才能保证正常运行
     *
     * @param context
     * @author 宋疆疆
     * @date 2014-4-15 下午3:37:17
     */
    public void init(Context context);

    /**
     * 调用完init方法后，即可调用此方法开启电视功能； 需要注意，调用完init，建议等待2秒再调用此方法
     *
     * @author 宋疆疆
     * @date 2014-4-15 下午3:38:20
     */
    public void startTvPlayer();

    /**
     * 设置监听切换频道时，频道的报错信息的接口；<br>
     * 注意1：此方法只能在调用完startTvPlayer()后使用；<br>
     * 注意2：changeSource() 会清空listener，所以要再次调用此方法
     *
     * @param listener
     * @author 宋疆疆
     * @date 2014年7月23日 上午10:59:47
     */
    public void setOnErrorListener(OnErrorListener listener);

    /**
     * 改变切换频道时的切换效果；此方法只能在调用startTvPlayer()方法之后再调用才起作用，并且切换过信号源后，需要重新调用此方法。
     *
     * @param freezeMode 如果为true，切换效果为镜像模式；如果为false，切换效果为黑屏效果
     * @author 宋疆疆
     * @date 2014年7月2日 下午2:27:37
     */
    public void setChannelChangeFreezeMode(boolean freezeMode);

    /**
     * 获取对应信号源的所有频道的信息；注意只有在天线类型设置为正确的类型后，才能成功获取频道信息
     *
     * @param source 只支持ATV和DTV
     * @return
     * @author 宋疆疆
     * @date 2014-4-15 下午4:20:45
     */
    public List<ProgramInfo> getProgramList(EnumInputSource source);

    /**
     * 获取当前频道的信息； 需要注意的是，DTV换台以后，需要延迟1秒才能获取当前频道信息
     *
     * @return
     * @author 宋疆疆
     * @date 2014-4-15 下午4:27:08
     */
    public ProgramInfo getCurrentProgramInfo();

    /**
     * 改变频道号，想换台就调用这个方法
     *
     * @param number 目标频道号，比如通过ProgramInfo.number获取的频道号
     * @author 宋疆疆
     * @date 2014-4-15 下午5:32:55
     */
    public boolean changeChannel(int number);

    /**
     * 改变信号源，比如从ATV切换到DTV
     *
     * @param sourcType 支持ATV、DTV、VGA、HDMI四种信号源
     * @author 宋疆疆
     * @date 2014-4-15 下午5:36:28
     */
    public void changeSource(EnumInputSource sourcType);

    /**
     * 改变天线类型
     *
     * @param antennaType
     * @author 宋疆疆
     * @date 2014年7月11日 下午3:57:23
     */
    public void changeAntennaType(EN_ANTENNA_TYPE antennaType);

    /**
     * 设置供电视显示用的view
     *
     * @param surfaceView
     * @author 宋疆疆
     * @date 2014-4-16 下午2:35:39
     */
    public void setSurfaceView(SurfaceView surfaceView);

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
    public void setTVSize(int x, int y, int width, int height);

    /**
     * 在不需要电视播放的时候，可以调用此方法，比如onPause方法里调用
     *
     * @author 宋疆疆
     * @date 2014-4-16 上午9:25:40
     */
    public void releaseSurfaceView();

    /**
     * 开始搜台的方法；注意一旦开始搜台，底层就会搜索到结束为止，如果想中途停止搜索，必须要调用stopTune()方法
     *
     * @param country
     * @author 宋疆疆
     * @date 2014年7月10日 下午4:35:10
     */
    public void startTune(MEMBER_COUNTRY country, EN_TUNING_SCAN_TYPE scantype, ScanListener listener);

    /**
     * 停止搜台的方法
     *
     * @author 宋疆疆
     * @date 2014年7月14日 下午2:36:39
     */
    public void stopTune();

    /**
     * 在程序退出时，你应该要调用此方法
     *
     * @author 宋疆疆
     * @date 2014-4-16 上午9:27:15
     */
    public void onDestroy();
}

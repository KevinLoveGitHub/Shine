package cn.shine.sdk.interfaces;

import android.content.Context;

/**
 * @auther 宋疆疆
 * @date 2015/5/8.
 */
public interface IDaemonManager {

    /**
     * 初始化方法，注意此类和Activity的生命周期要挂钩，建议在onCreate方法调用init函数；并且调用此方法后，建议等待3秒后再调用其他方法
     * ，否则可能报空指针异常
     *
     * @author 宋疆疆
     * @date 2014年7月8日 上午11:18:06
     * @param context
     * @return 返回true为初始化成功，false为初始化失败
     */
    public boolean init(Context context);

    /**
     * 关机的方法
     *
     * @author 宋疆疆
     * @date 2014年7月8日 上午11:20:53
     * @return 返回false的话，请尝试调用init方法,并等待3秒
     */
    public boolean shutdown();

    /**
     * 传入一个时间字符串，来设置开机时间
     *
     * @author 宋疆疆
     * @date 2014年7月8日 上午11:25:44
     * @param time
     *            使用字符串来表示时间，格式必须严格遵守"2014-03-03 23:03:23"样式
     * @return 返回false的话，请尝试调用init方法,并等待3秒
     */
    public boolean setPowerWakeTime(String time);

    /**
     * 建议在Activity的onDestory方法调用此方法
     *
     * @author 宋疆疆
     * @date 2014年7月8日 上午11:33:31
     */
    public void onDestory();
}

package cn.shine.sdk.tv.listener;

/**
 * 搜索频道时的回调接口，每搜索到一个频道，就会调用此接口
 * 
 * @author 宋疆疆
 * @date 2014年7月14日 下午2:30:00
 */
public interface ScanListener {
	/**
	 * 
	 * @author 宋疆疆
	 * @date 2014年7月14日 下午2:30:39
	 * @param type
	 *            0代表搜索到的是ATV的频道，1代表DTV
	 * @param channelNum
	 *            搜索到的频道对应的频道号
	 * @param percent
	 *            搜索的进度
	 * @param frequency
	 *            搜索到的频道对应的频点
	 */
	void onScanChanged(int type, int channelNum, int percent, int frequency);
}

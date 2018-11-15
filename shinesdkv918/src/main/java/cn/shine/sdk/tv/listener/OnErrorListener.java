package cn.shine.sdk.tv.listener;

import cn.shine.sdk.tv.enums.ErrorCode;

/**
 * 切换频道时，频道报错时的回调监听接口
 * 
 * @author 宋疆疆
 * @date 2014年7月23日 上午10:58:24
 */
public interface OnErrorListener {
	void onError(ErrorCode error);
}

package cn.shine.sdk.tv.enums;

/**
 * 切换频道时，可能会报错的错误码
 * 
 * @author 宋疆疆
 * @date 2014年7月23日 上午10:57:43
 */
public enum ErrorCode {
	/**
	 * 无信号
	 */
	NO_SIGNAL,
	/**
	 * 无效服务
	 */
	INVALID_SERVICE,
	/**
	 * 未插CI卡
	 */
	NO_CI_MODULE,
	/**
	 * CI+ 认证
	 */
	CI_PLUS_AUTHENTICATION,
	/**
	 * 加密节目
	 */
	SCRAMBLED_PROGRAM,
	/**
	 * 锁定节目
	 */
	PROGRAM_BLOCK,
	/**
	 * 父母锁定
	 */
	PARENTAL_BLOCK,
	/**
	 * 音频节目
	 */
	AUDIO_ONLY,
	/**
	 * 数据节目
	 */
	DATA_ONLY,
	/**
	 * 不支持
	 */
	UNSUPPORTED,
	/**
	 * 无效 PMT
	 */
	INVALID_PMT,
	/**
	 * 自适应
	 */
	AUTO_ADJUST,
	/**
	 * 未知
	 */
	UNKOWN, 
	/**
	 * 获取到信号
	 */
	GET_SINGLE
}

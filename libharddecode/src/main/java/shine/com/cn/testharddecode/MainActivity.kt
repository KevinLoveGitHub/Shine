package shine.com.cn.testharddecode

/**
 * @author Kevin
 * @data 2018/9/27
 */
class MainActivity {

    external fun stringFromJNI(): String

    external fun add(x: Int, y: Int): Int

    external fun startAllNetStreamWork(): Int

    external fun getH264Frame(resultImage: ByteArray,
                              nChannelDec: Int,
                              nSleepMs: Int
    ): Int

    //0 ok other stop
    external fun getChannelWorkStatus(nChannelDec: Int): Int

    companion object {
        private const val TAG = "ShineHardDecode"

        init {
            System.loadLibrary("shineHardDecode")
        }
    }
}
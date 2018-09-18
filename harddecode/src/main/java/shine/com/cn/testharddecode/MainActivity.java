package shine.com.cn.testharddecode;

/**
 * @author Kevin
 * @data 2018/9/11
 */
public class MainActivity {
    static {
        System.loadLibrary("shineHardDecode");
    }

    public native String stringFromJNI();

    public native int add(int x, int y);

    public native int startAllNetStreamWork();

    public native int getH264Frame(byte[] resultImage,
                                   int nChannelDec,
                                   int nSleepMs
    );

    //0 ok other stop
    public native int getChannelWorkStatus(int nChannelDec);
}

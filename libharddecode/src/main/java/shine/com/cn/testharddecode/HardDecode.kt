package shine.com.cn.testharddecode

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.SurfaceHolder
import java.util.*

/**
 * @author Kevin
 * @data 2018/9/27
 */
object HardDecode {
    private const val TAG = "HardDecode"
    private val shineHardDecode: MainActivity = MainActivity()
    private lateinit var videoDecoder: MrVideoDecoder
    private lateinit var holder: SurfaceHolder
    private var nChannelWorkStatusLast = 1

    init {
        shineHardDecode.startAllNetStreamWork()
    }

    @SuppressLint("HandlerLeak")
    private var handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            if (msg.what == 1) {
                val nNetWorkStatus = shineHardDecode.getChannelWorkStatus(0)
                Log.i(TAG, nNetWorkStatus.toString())
                if (nNetWorkStatus == 0) {
                    if (nChannelWorkStatusLast != 0) {
                        Log.d(TAG, "createDecoder")
                        videoDecoder.createDecoder(1920, 1080, "video/avc", holder.surface)
                    }

                    val bufFromNet = ByteArray(1920 * 1080)
                    val nRet = shineHardDecode.getH264Frame(bufFromNet, 0, 1000)
                    if (nRet > 0) {
                        videoDecoder.decodeFrame(bufFromNet, nRet)
                    }
                    nChannelWorkStatusLast = nNetWorkStatus
                } else {
                    if (nChannelWorkStatusLast == 0) {
                        Log.d(TAG, "release")
                        videoDecoder.release()
                        nChannelWorkStatusLast = nNetWorkStatus
                    }
                }
            }
            super.handleMessage(msg)
        }
    }

    private var timer = Timer()
    private var timerTask: TimerTask = object : TimerTask() {
        override fun run() {
            val msg = Message()
            msg.what = 1
            handler.sendMessage(msg)
        }
    }

    fun setSurfaceHolder(surfaceHolder: SurfaceHolder) {
        holder = surfaceHolder
        surfaceHolder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
                videoDecoder = MrVideoDecoder()
            }

            override fun surfaceChanged(surfaceHolder: SurfaceHolder, i: Int, i1: Int, i2: Int) {

            }

            override fun surfaceDestroyed(surfaceHolder: SurfaceHolder) {
                videoDecoder.release()
            }
        })

        timer.schedule(timerTask, 1000, 33)
    }

    fun release() {
        timer.cancel()
        handler.removeCallbacksAndMessages(null)

        if (::videoDecoder.isInitialized) {
            videoDecoder.release()
        }
    }
}
package com.shine.harddecode

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.SurfaceHolder
import kotlinx.android.synthetic.main.activity_main.*
import shine.com.cn.testharddecode.MainActivity

class FirstActivity : AppCompatActivity() {

    private var decoder: MrVideoDecoder? = null
    private var nChannel0WorkStatusLast = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MainActivity().startAllNetStreamWork()
//        initView()
//        Flowable.interval(1,1, TimeUnit.SECONDS)
//                .subscribe {
//                    decoder?.apply {
//                        val nNetWorkStatus = MainActivity.getChannelWorkStatus(0)
//                        if (nNetWorkStatus == 0) {
//                            if (nChannel0WorkStatusLast != 0) {
//                                createDecoder(640, 480, "video/avc", sv_camera_1.holder.surface)
//                            }
//                            val bufFromNet = ByteArray(1920 * 1080)
//                            val nRet = MainActivity.getH264Frame(bufFromNet, 0, 3000)
//                            if (nRet > 0) {
//                                decodeFrame(bufFromNet, nRet)
//                            }
//                            nChannel0WorkStatusLast = nNetWorkStatus
//                        } else {
//                            if (nChannel0WorkStatusLast == 0) {
//                                release()
//                                nChannel0WorkStatusLast = nNetWorkStatus
//                            }
//                        }
//                    }
//                }
    }

    private fun initView() {
        sv_camera_1.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(p0: SurfaceHolder?) {
                decoder = MrVideoDecoder()
            }

            override fun surfaceDestroyed(p0: SurfaceHolder?) {
            }

            override fun surfaceChanged(p0: SurfaceHolder?, p1: Int, p2: Int, p3: Int) {
                decoder?.release()
            }
        })


    }


}

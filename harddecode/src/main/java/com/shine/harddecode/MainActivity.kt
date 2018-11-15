package com.shine.harddecode

import android.app.Activity
import android.content.Context
import android.media.AudioManager
import android.os.Bundle
import android.view.SurfaceView
import android.view.View
import shine.com.cn.testharddecode.MrVideoDecoder
import kotlin.concurrent.thread

class MainActivity : Activity() {
    lateinit var view1: SurfaceView
    private var decoder: MrVideoDecoder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        view1 = findViewById(R.id.sv_camera_1)
//        HardDecode.setSurfaceHolder(view1.holder)
    }

    fun close(view: View) {
        val audioService: AudioManager = this.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        thread {
            audioService.setParameters("ForceCloseCard0Dev0")
        }
    }


    override fun onDestroy() {
//        if (decoder != null) {
//            Log.d(TAG, "onDestroy decoder release----------------")
//            decoder!!.release()
//        }

        super.onDestroy()
    }

    companion object {
        private val TAG = "testharddecode"
    }
}

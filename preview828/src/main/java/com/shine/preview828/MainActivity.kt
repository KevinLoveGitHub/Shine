package com.shine.preview828

import android.os.Bundle
import android.os.SystemClock
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import cn.shine.sdk.engine.ShineSDKHelper
import cn.shine.sdk.interfaces.ITvPlayerManager
import cn.shine.sdk.tv.enums.EnumInputSource
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mTvPlayerManager: ITvPlayerManager
    private lateinit var mCameraOperator: CameraOperator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mTvPlayerManager = ShineSDKHelper.getTvPlayerManager(this)
        mTvPlayerManager.init(this)
        mTvPlayerManager.setSurfaceView(sv_preview)
        mTvPlayerManager.changeSource(EnumInputSource.E_INPUT_SOURCE_ATV)

        mCameraOperator = CameraOperator()
        mCameraOperator.receiveThread()
    }

    fun start(view: View) {
        mTvPlayerManager.changeSource(EnumInputSource.E_INPUT_SOURCE_HDMI)
        mTvPlayerManager.startTvPlayer()
        mTvPlayerManager.setOnErrorListener {
            Log.e(TAG, it.name)
        }
        mTvPlayerManager.setChannelChangeFreezeMode(true)
        SystemClock.sleep(1000)
        val location = IntArray(2)
        sv_preview.getLocationOnScreen(location)
        val x = location[0]
        val y = location[1]
        val width = sv_preview.width
        val height = sv_preview.height
        mTvPlayerManager.setTVSize(x, y, width, height)
    }

    fun top(view: View) {
        mCameraOperator.handleInstruction(CameraOperator.Up, CameraOperator.Stop)
    }

    fun bottom(view: View) {
        mCameraOperator.handleInstruction(CameraOperator.Down, CameraOperator.Stop)

    }

    fun left(view: View) {
        mCameraOperator.handleInstruction(CameraOperator.Left, CameraOperator.Stop)

    }

    fun right(view: View) {
        mCameraOperator.handleInstruction(CameraOperator.Right, CameraOperator.Stop)
    }

    fun zoomIn(view: View) {
        mCameraOperator.handleInstruction(CameraOperator.ZoomIn, CameraOperator.ZoomStop)
    }

    fun zoomOut(view: View) {
        mCameraOperator.handleInstruction(CameraOperator.ZoomUp, CameraOperator.ZoomStop)
    }

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onDestroy() {
        super.onDestroy()
        mCameraOperator.exit()
        mTvPlayerManager.releaseSurfaceView()
        mTvPlayerManager.onDestroy()
    }
}

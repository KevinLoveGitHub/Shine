package org.lovedev.chang_stream_828

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import cn.shine.sdk.engine.ShineSDKHelper
import cn.shine.sdk.interfaces.ITvPlayerManager
import cn.shine.sdk.tv.enums.EnumInputSource
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var mTvPlayerManager: ITvPlayerManager
    private lateinit var mSharedPreferences: SharedPreferences
    private var preKeyCode = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mSharedPreferences = application.getSharedPreferences(Companion.SP_NAME, Context.MODE_PRIVATE)
        val code = mSharedPreferences.getInt(KEY_CODE_INDEX, -1)
        mTvPlayerManager = ShineSDKHelper.getTvPlayerManager(this)
        mTvPlayerManager.init(this)
        mTvPlayerManager.setSurfaceView(sv_preview)
        if (KeyEvent.KEYCODE_2 == code) {
            mTvPlayerManager.changeSource(EnumInputSource.E_INPUT_SOURCE_VGA)
        } else {
            mTvPlayerManager.changeSource(EnumInputSource.E_INPUT_SOURCE_HDMI)
        }

    }


    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        if (preKeyCode == KeyEvent.KEYCODE_1 && keyCode == KeyEvent.KEYCODE_ENTER) {
            mTvPlayerManager.changeSource(EnumInputSource.E_INPUT_SOURCE_HDMI)
            mSharedPreferences.edit().putInt(KEY_CODE_INDEX, KeyEvent.KEYCODE_1).apply()
        }

        if (preKeyCode == KeyEvent.KEYCODE_2 && keyCode == KeyEvent.KEYCODE_ENTER) {
            mTvPlayerManager.changeSource(EnumInputSource.E_INPUT_SOURCE_VGA)
            mSharedPreferences.edit().putInt(KEY_CODE_INDEX, KeyEvent.KEYCODE_2).apply()
        }
        preKeyCode = keyCode
        return super.onKeyUp(keyCode, event)
    }

    companion object {
        private const val SP_NAME = "stream_index"
        private const val  KEY_CODE_INDEX = "key_code_index"

    }
}

package com.shine.mmplayer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.ics.mm.PlayerManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mPlayerManager: PlayerManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mPlayerManager = PlayerManager()
        mPlayerManager.deinit()
    }

    fun play(view: View) {
        playLiver("shine_av_stream://tcp@172.168.11.110:5000")
    }

    private fun playLiver(url: String): Int {
        Log.d(TAG, "playLiver() called with: url = [$url]")
        mPlayerManager.setRecvTimeOut(5)
        val ret = mPlayerManager.setContentSource(url)
        if (ret == 0) {
            mPlayerManager.setDisplay(sv_player.holder)
            mPlayerManager.showFriendlyFrame()
            mPlayerManager.play(url)

        } else {
            Log.e(TAG, "fail to set content source")
        }
        return ret
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}

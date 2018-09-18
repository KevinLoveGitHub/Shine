package lovedev.org.text_to_speech

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.Environment
import android.os.SystemClock
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.LANG_AVAILABLE
import android.speech.tts.TextToSpeech.LANG_COUNTRY_AVAILABLE
import android.speech.tts.UtteranceProgressListener
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import org.lovedev.util.UDPHelper
import java.io.File
import java.util.*
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {

    private lateinit var mTextToSpeech: TextToSpeech
    private lateinit var suClient: SuClient
    private val enginePackageName = "com.iflytek.speechcloud"
    private val broadcastReceiver = MyBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val filter = IntentFilter()
        filter.addAction("telpo.intent.action.HANDLE_PLUG");
        filter.addAction("com.android.server.PhoneWindowManager.action.EXTKEYEVENT");
        registerReceiver(broadcastReceiver, filter)
        thread {
            suClient = SuClient()
            suClient.init(null)
        }

        //实例并初始化TTS对象
        mTextToSpeech = TextToSpeech(this, TextToSpeech.OnInitListener { status ->
            if (status == TextToSpeech.SUCCESS) {
                mTextToSpeech.setEngineByPackageName(enginePackageName)
                mTextToSpeech.setSpeechRate(0.5f)
                //设置朗读语言
                val supported = mTextToSpeech.setLanguage(Locale.CHINA)

                if (supported != LANG_AVAILABLE && supported != LANG_COUNTRY_AVAILABLE) {
                    Toast.makeText(this@MainActivity, "不支持当前语言！", Toast.LENGTH_LONG).show()
                }
            }
        })

        mTextToSpeech.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
            override fun onDone(p0: String?) {
                Log.i(TAG, "onDone $p0")
                val currentThreadTimeMillis = SystemClock.currentThreadTimeMillis()
                if (!isCancelSpeak) {
                    mTextToSpeech.speak("[n1] 123456床呼叫_$currentThreadTimeMillis [n1]", TextToSpeech.QUEUE_FLUSH, null,
                            currentThreadTimeMillis.toString())
                }

            }

            override fun onError(p0: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onStart(p0: String?) {
                Log.i(TAG, "onStart $p0")
            }

        })
    }

    private var isCancelSpeak = false

    fun speak(view: View) {
        isCancelSpeak = false
        val currentThreadTimeMillis = SystemClock.currentThreadTimeMillis()
        val fileName = "test_$currentThreadTimeMillis.wav"
        val path = Environment.getExternalStorageDirectory().path + File.separator + fileName
        mTextToSpeech.speak("[n1] 123床呼叫_$currentThreadTimeMillis [n1]", TextToSpeech.QUEUE_FLUSH, null, currentThreadTimeMillis
                .toString())
        val time = SystemClock.currentThreadTimeMillis() - currentThreadTimeMillis
        Log.i("start", time.toString())
    }

    fun startM(view: View) {
//        stopSpeak()
        thread {
//            suClient.execCMD("talk -m 172.168.44.81:7000 ")
            suClient.execCMD("tinymix 85 1")
        }
    }

    fun startP(view: View) {
//        stopSpeak()
        thread {
            suClient.execCMD("talk -p 7000 -ic 0 -id 0 -il 1 -oc 0 -od 12 -ol 1 ; ")
        }
    }

    fun stop(view: View) {
        UDPHelper.sendUDPMessage("quit", "127.0.0.1", 12300)
    }

    private fun stopSpeak() {
        mTextToSpeech.stop()
        isCancelSpeak = true
    }

    inner class MyBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            this@MainActivity.startM(View(this@MainActivity))
            val sb = StringBuilder()
            sb.append("Action: " + intent.getAction() + "\n")
            sb.append("URI: " + intent.toUri(Intent.URI_INTENT_SCHEME).toString() + "\n")
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}

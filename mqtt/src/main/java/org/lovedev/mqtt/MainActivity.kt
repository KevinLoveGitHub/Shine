package org.lovedev.mqtt

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import org.eclipse.paho.client.mqttv3.*
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {

    lateinit var client: MqttClient
    lateinit var options: MqttConnectOptions
    val host = "ws://58.87.94.78:61614"
    lateinit var scheduler: ScheduledExecutorService

    private val callback = Handler.Callback {
        println(it)
        return@Callback false
    }
    val handler = Handler(callback)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        startReconnect()
    }

    private fun startReconnect() {
        scheduler = Executors.newSingleThreadScheduledExecutor()
        scheduler.scheduleAtFixedRate(Runnable {
            if (!client.isConnected) {
                connect()
            }
        }, 1 * 1000, 10 * 1000, TimeUnit.MILLISECONDS)
    }

    private fun init() {
        try {
            //host为主机名，test为clientid即连接MQTT的客户端ID，一般以客户端唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
            client = MqttClient(host, "test1",
                    MemoryPersistence())
            //MQTT的连接设置
            options = MqttConnectOptions()
            //设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
            options.isCleanSession = true
            //设置连接的用户名
            options.userName = "admin"
            //设置连接的密码
            options.password = "admin".toCharArray()
            // 设置超时时间 单位为秒
            options.connectionTimeout = 10
            // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
            options.keepAliveInterval = 20
            //设置回调
            client.setCallback(object : MqttCallback {

                override fun connectionLost(cause: Throwable) {
                    //连接丢失后，一般在这里面进行重连
                    println(cause.message)
                    cause.printStackTrace()
                }

                override fun deliveryComplete(token: IMqttDeliveryToken) {
                    //publish后会执行到这里
                    System.out.println("deliveryComplete---------" + token.isComplete())
                }

                @Throws(Exception::class)
                override fun messageArrived(topicName: String, message: MqttMessage) {
                    //subscribe后得到的消息会执行到这里面
                    println("messageArrived----------")
                    val msg = Message()
                    msg.what = 1
                    msg.obj = topicName + "---" + message.toString()
                    handler.sendMessage(msg)
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onResume() {
        super.onResume()
        connect()
    }


    private fun connect() {
        thread {  }
//        thread {
            client.connect(options)
//        }
    }

    override fun onDestroy() {
        client.disconnect()
        super.onDestroy()
    }
}

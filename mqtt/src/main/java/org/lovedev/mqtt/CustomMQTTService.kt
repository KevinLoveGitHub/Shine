package org.lovedev.mqtt

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.util.Log
import org.eclipse.paho.client.mqttv3.*
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence


class CustomMQTTService : Service() {
    val TAG = "MessengerService"
    private val serverUri = "ws://10.0.1.86:8083"
    private var clientId = "ExampleAndroidClient"
    private val publishTopic = "exampleAndroidPublishTopic"
    lateinit var mqttAndroidClient: MqttClient


    // 2. 自定义 Handler 对象接收并处理消息
    private class MessengerHandler : Handler() {
        override fun handleMessage(msg: Message) {
            Log.i("MessengerService", "MessengerService received message: " + msg.data)
        }
    }

    // 1. 创建 Messenger 对象
    private val mMessenger = Messenger(MessengerHandler())

    override fun onBind(intent: Intent): IBinder {
        return mMessenger.binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("onStartCommand", "onStartCommand")
        clientId += System.currentTimeMillis()
        mqttAndroidClient = MqttClient(serverUri, clientId, MemoryPersistence())
        mqttAndroidClient.setCallback(object : MqttCallbackExtended {
            override fun connectComplete(reconnect: Boolean, serverURI: String) {

            }

            override fun connectionLost(cause: Throwable) {
                cause.printStackTrace()
            }

            @Throws(Exception::class)
            override fun messageArrived(topic: String, message: MqttMessage) {

            }

            override fun deliveryComplete(token: IMqttDeliveryToken) {

            }
        })

        val mqttConnectOptions = MqttConnectOptions()
        mqttConnectOptions.isAutomaticReconnect = true
        mqttConnectOptions.isCleanSession = false
        mqttConnectOptions.userName = "shine"
        mqttConnectOptions.password = "shine".toCharArray()

        try {
            mqttAndroidClient.connect(mqttConnectOptions)
        } catch (ex: MqttException) {
            ex.printStackTrace()
        }

        return super.onStartCommand(intent, flags, startId)
    }

    fun subscribeToTopic() {
        try {
            mqttAndroidClient.subscribe(publishTopic, 1) { topic, message ->
                println("Message: " + topic + " : " + String(message.payload))
            }

        } catch (ex: MqttException) {
            System.err.println("Exception whilst subscribing")
            ex.printStackTrace()
        }
    }
}

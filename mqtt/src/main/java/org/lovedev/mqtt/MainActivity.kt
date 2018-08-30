package org.lovedev.mqtt

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.eclipse.paho.client.mqttv3.MqttClient


class MainActivity : AppCompatActivity() {
    private val serverUri = "ws://10.0.1.86:8083"
    private var clientId = "ExampleAndroidClient"
    private val publishTopic = "exampleAndroidPublishTopic"
    lateinit var mqttAndroidClient: MqttClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(this, CustomMQTTService::class.java)
        startService(intent)
    }
}

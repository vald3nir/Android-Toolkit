package com.vald3nir.toolkit.core.services.iot

import com.hivemq.client.mqtt.datatypes.MqttQos
import com.hivemq.client.mqtt.mqtt5.Mqtt5Client
import java.util.UUID

class MQTTClient(brokenAddress: String) {

    private val client = Mqtt5Client.builder()
        .identifier(UUID.randomUUID().toString())
        .serverHost(brokenAddress)
        .buildAsync()

    fun connect() {
        client.connect()
    }

    fun disconnect() {
        client.disconnect()
    }

    fun publish(topic: String, payload: ByteArray) {
        client.publishWith()
            .topic(topic)
            .qos(MqttQos.AT_LEAST_ONCE)
            .retain(true)
            .payload(payload)
            .send();
    }

    fun subscribe(topic: String, onResponse: (jsonResponse: String) -> Unit) {
        client.toAsync().subscribeWith()
            .topicFilter(topic)
            .qos(MqttQos.EXACTLY_ONCE)
            .callback(({ publish ->
                run {
                    onResponse.invoke(String(publish.payloadAsBytes))
                }
            })).send()
    }

    fun unsubscribe(topic: String) {
        client.toAsync().unsubscribeWith()
            .topicFilter(topic)
            .send()
    }
}
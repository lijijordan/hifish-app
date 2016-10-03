package com.hifish.app.test;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * Uses JsonPath: http://goo.gl/nwXpb, Hamcrest and MockMVC
 */


public class MqttFish {

    public static void main(String[] args) {
        String content = "Message from liji's IntellJIDEA:";
        int qos = 0;
        String broker = "tcp://106.185.35.79:1883";
        String clientId = "mqtt1";
//        MemoryPersistence persistence = new MemoryPersistence();
        MqttDefaultFilePersistence persistence = new MqttDefaultFilePersistence("/Users/liji/test");
        try {
//            MqttClient mqttClient = new MqttClient(broker, clientId, persistence);
            MqttAsyncClient sampleClient = new MqttAsyncClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            DisconnectedBufferOptions op = new DisconnectedBufferOptions();
            op.setBufferEnabled(true);
            op.setBufferSize(100);
            sampleClient.setBufferOpts(op);
//            connOpts.setMaxInflight(100);
            connOpts.setCleanSession(true);
            connOpts.setUserName("root");
            connOpts.setPassword("fisher".toCharArray());
            connOpts.setCleanSession(true);
            System.out.println("Connecting to broker: " + broker);
            sampleClient.connect(connOpts).waitForCompletion();
            System.out.println("Connected");
            System.out.println("Publishing message: " + content.toString());
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            sampleClient.subscribe("PH", qos, (s, mqttMessage) -> {
                System.out.println("PH          : " + new String(mqttMessage.getPayload(), StandardCharsets.UTF_8));
            });
            sampleClient.subscribe("Temperature", qos, (s, mqttMessage) -> {
                System.out.println("Temperature : " + new String(mqttMessage.getPayload(), StandardCharsets.UTF_8));
            });
            sampleClient.subscribe("TDS", qos, (s, mqttMessage) -> {
                System.out.println("TDS         : " + new String(mqttMessage.getPayload(), StandardCharsets.UTF_8));
                System.out.println(new Date());
                System.out.println("------------------------------------------------------------------------");
            });
            System.out.println("Message published");
//            sampleClient.disconnect();
            System.out.println("Disconnected");
//            System.exit(0);

//            for (int i = 0; i < 1000; i++) {
//                Thread.sleep(1000);
//                System.out.println("send..." + i);
//                sampleClient.publish("temperature", new MqttMessage(("123456-22.21").getBytes()));
//                sampleClient.publish("PH", new MqttMessage(("123456-7.22").getBytes()));
//            }

        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
    }

}

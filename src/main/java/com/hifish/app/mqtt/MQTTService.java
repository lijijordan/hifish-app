package com.hifish.app.mqtt;

import com.hifish.app.entity.AirDeviceData;
import com.hifish.app.service.AirDeviceManager;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.nio.charset.StandardCharsets;

/**
 * Created with IntelliJ IDEA.
 * User: liji
 * Date: 16/8/23
 * Time: 下午6:41
 * To change this template use File | Settings | File Templates.
 */
public class MQTTService {

    private static final Logger log = LoggerFactory.getLogger(MQTTService.class);
    /**
     * 设备离线遗言频道
     */
    private static final String SMART_DEVICE_WILL = "smartDeviceWill";
    private static MqttAsyncClient asyncClient;
    private static int qos = 0;
    private static String serverTopic;
    private static ApplicationContext context;
    @Autowired
    private static AirDeviceManager deviceManager;

    /**
     * Gets async client.
     *
     * @return the async client
     */
    public static MqttAsyncClient getAsyncClient() {
        return asyncClient;
    }


    /**
     * A
     * Init mqtt.
     *
     * @param applicationContext the application context
     */
    public static void initMQTT(ApplicationContext applicationContext) {
        log.info("初始化MQTT连接");
        context = applicationContext;

        deviceManager = context.getBean(AirDeviceManager.class);
        String broker = MQTTConfig.getBroker();
        String clientId = MQTTConfig.getClientId();
        String userName = MQTTConfig.getUserName();
        String password = MQTTConfig.getPassword();
        String deviceWillTopic = MQTTConfig.getDeviceWillTopic();
        // 服务器订阅的Topic用于数据通信
        serverTopic = MQTTConfig.getTopic();
        qos = MQTTConfig.getQos();
        MemoryPersistence persistence = new MemoryPersistence();
        try {
            asyncClient = new MqttAsyncClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            DisconnectedBufferOptions op = new DisconnectedBufferOptions();
            op.setBufferEnabled(true);
            op.setBufferSize(100);
            connOpts.setMaxInflight(40);
            connOpts.setCleanSession(false);
            connOpts.setKeepAliveInterval(10);
            connOpts.setUserName(userName);
            connOpts.setWill(SMART_DEVICE_WILL, serverTopic.getBytes(), 1, true);
            connOpts.setPassword(password.toCharArray());
            log.info("Connecting to broker: " + broker);
            asyncClient.connect(connOpts).waitForCompletion();
            subscripe(deviceWillTopic, serverTopic);
            asyncClient.setCallback(new DeviceMQTTCallBack(connOpts, serverTopic, deviceWillTopic));
        } catch (MqttException me) {
            me.printStackTrace();
        }
    }

    /**
     * Subscripe.
     *
     * @param deviceWillTopic the device will topic
     * @param serverTopic     the server topic
     * @throws MqttException the mqtt exception
     */
    public static void subscripe(String deviceWillTopic, String serverTopic) throws MqttException {
        subscribeWillTopic(deviceWillTopic);
        subscribeServerTopic(serverTopic);
    }


    /**
     * Subscribe will topic.
     *
     * @param deviceWillTopic the device will topic
     * @throws MqttException the mqtt exception
     */
    private static void subscribeWillTopic(String deviceWillTopic) throws MqttException {
        log.info("*******************************************************");
        log.info("* 订阅频道: {}", deviceWillTopic);
        asyncClient.subscribe(deviceWillTopic, qos, (s, mqttMessage) -> {
            // 更新状态
            String macAddress = new String(mqttMessage.getPayload(), StandardCharsets.UTF_8);
            log.info("topic:{}, 设备离线: {}", deviceWillTopic, macAddress);
            // 处理离线消息..
        });
    }

    /**
     * Subscripe topic.
     *
     * @param serverTopic the topic
     * @throws MqttException the mqtt exception
     */
    private static void subscribeServerTopic(String serverTopic) throws MqttException {
        log.info("*******************************************************");
        log.info("* 订阅频道: {}", serverTopic);
        asyncClient.subscribe(serverTopic, qos, (s, mqttMessage) -> {
            try {
                String content = new String(mqttMessage.getPayload(), StandardCharsets.UTF_8);
                log.info("Client message : " + content);
                saveAirDeviceData(content);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Save Air device data
     *
     * @param content
     */
    private static void saveAirDeviceData(String content) {
        JSONObject jsonObject = new JSONObject(content);
        // 处理消息..
        String sn = jsonObject.getString("sn");
        double temperature = jsonObject.getDouble("temperature");
        double humidity = jsonObject.getDouble("humidity");
        int pm2_5 = jsonObject.getInt("pm2_5");
        AirDeviceData airDevice = new AirDeviceData();
        airDevice.setCreateTime(new java.util.Date());
        airDevice.setDeviceId(sn);
        airDevice.setHumidity(humidity);
        airDevice.setPm2_5(pm2_5);
        airDevice.setTemperature(temperature);
        deviceManager.saveAirDeviceData(airDevice);
    }

    /**
     * The type Device mqtt call back.
     */
    static class DeviceMQTTCallBack implements MqttCallback {
        /**
         * The Conn opts.
         */
        MqttConnectOptions connOpts;
        /**
         * The Device will topic.
         */
        String deviceWillTopic;
        /**
         * The Server topic.
         */
        String serverTopic;


        /**
         * Instantiates a new Device mqtt call back.
         *
         * @param connOpts        the conn opts
         * @param serverTopic     the server topic
         * @param deviceWillTopic the device will topic
         */
        public DeviceMQTTCallBack(MqttConnectOptions connOpts, String serverTopic, String deviceWillTopic) {
            this.connOpts = connOpts;
            this.serverTopic = serverTopic;
            this.deviceWillTopic = deviceWillTopic;
        }

        @Override
        public void connectionLost(Throwable cause) {
            try {
                while (!asyncClient.isConnected()) {
                    log.info("服务器掉线, 5s后重新连接!");
                    Thread.sleep(5000 * 1);
//                    log.info("自动重连....");
                    if (asyncClient != null) {
                        asyncClient.close();
                    }
                    initMQTT(context);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void messageArrived(String topic, MqttMessage message) throws Exception {
        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken token) {
        }
    }


}

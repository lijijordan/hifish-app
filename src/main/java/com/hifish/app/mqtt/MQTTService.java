package com.hifish.app.mqtt;

import com.mydreamplus.smartdevice.api.rest.DeviceController;
import com.mydreamplus.smartdevice.config.MQTTConfig;
import com.mydreamplus.smartdevice.dao.jpa.DeviceRepository;
import com.mydreamplus.smartdevice.dao.jpa.PIRespository;
import com.mydreamplus.smartdevice.domain.DeviceStateEnum;
import com.mydreamplus.smartdevice.domain.in.*;
import com.mydreamplus.smartdevice.entity.PI;
import com.mydreamplus.smartdevice.util.JsonUtil;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Date;

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
    private static PIRespository pIRespository;
    private static DeviceController deviceController;
    private static DeviceRepository deviceRepository;

    private static String testTopic;
    private static String serverTopic;

    private static ApplicationContext context;


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
        deviceController = applicationContext.getBean(DeviceController.class);
        pIRespository = applicationContext.getBean(PIRespository.class);
        deviceRepository = applicationContext.getBean(DeviceRepository.class);

        String broker = MQTTConfig.getBroker();
        String clientId = MQTTConfig.getClientId();
        String userName = MQTTConfig.getUserName();
        String password = MQTTConfig.getPassword();
        String deviceWillTopic = MQTTConfig.getDeviceWillTopic();
        // 服务器订阅的Topic用于数据通信
        serverTopic = MQTTConfig.getTopic();
        testTopic = MQTTConfig.getTestTopic();

        qos = MQTTConfig.getQos();
        MemoryPersistence persistence = new MemoryPersistence();
        try {
            asyncClient = new MqttAsyncClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            DisconnectedBufferOptions op = new DisconnectedBufferOptions();
            op.setBufferEnabled(true);
            op.setBufferSize(100);

            /*
            tls start
             */
            final char[] passphrase = "bunnies".toCharArray();
            KeyStore ks = KeyStore.getInstance("PKCS12");
            ks.load(new FileInputStream(MQTTConfig.getClientKey()), passphrase);
//            ks.load(new FileInputStream(applicationContext.getResource("client_key.p12").getFile()), passphrase);

            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            kmf.init(ks, passphrase);

            // server certificate
            KeyStore tks = KeyStore.getInstance("JKS");
            // created the key store with
            // keytool -importcert -alias rmq -file ./server_certificate.pem -keystore ./jvm_keystore
            tks.load(new FileInputStream(MQTTConfig.getKeyStore()), passphrase);
//            tks.load(new FileInputStream(applicationContext.getResource("jvm_keystore").getFile()), passphrase);
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
            tmf.init(tks);

            SSLContext ctx = SSLContext.getInstance("SSLv3");
            ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
            connOpts.setSocketFactory(ctx.getSocketFactory());
            /*
            tls end
             */
            connOpts.setMaxInflight(40);
            connOpts.setCleanSession(false);
            connOpts.setKeepAliveInterval(10);
//            connOpts.setAutomaticReconnect(true);
            connOpts.setUserName(userName);
            connOpts.setWill(SMART_DEVICE_WILL, serverTopic.getBytes(), 1, true);
            connOpts.setPassword(password.toCharArray());
            log.info("Connecting to broker: " + broker);
            asyncClient.connect(connOpts).waitForCompletion();
            subscripe(deviceWillTopic, serverTopic);
            asyncClient.setCallback(new DeviceMQTTCallBack(connOpts, serverTopic, deviceWillTopic));
        } catch (MqttException me) {
            me.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
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
        subscribeTestTopic();
    }

    /**
     * 订阅测试Topic
     *
     * @throws MqttException
     */
    private static void subscribeTestTopic() throws MqttException {
        log.info("*******************************************************");
        log.info("* 订阅频道测试频道: {}", testTopic);
        log.info("*******************************************************");
        asyncClient.subscribe(testTopic, qos, (s, mqttMessage) -> {
            // 更新状态
            String piMacaddress = new String(mqttMessage.getPayload(), StandardCharsets.UTF_8);
            log.info("测试请求来源设备: {}", piMacaddress);
            deviceController.test(piMacaddress, serverTopic);
        });
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
            PI pi = pIRespository.findByMacAddress(macAddress);
            if (pi != null) {
                pi.setOffLine(true);
                pi.setUpdateTime(new Date());
                pIRespository.save(pi);
            } else {
                // 其他非网关设备
                deviceRepository.findAllByMacAddress(macAddress).forEach(device -> {
                    log.info("更新设备为离线状态:{}", device.getMacAddress());
                    device.setDeviceState(DeviceStateEnum.OFFLINE);
                    deviceRepository.save(device);
                });
            }
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
                JSONObject jsonObject = new JSONObject(content);
                String action = (String) jsonObject.get("action");
                log.info("Action ========================>{} {}", action, content);
                switch (action) {
                    case "regPi": {
                        PIRegisterRequest registerRequest = JsonUtil.getEntity(content, PIRegisterRequest.class);
                        deviceController.registerPi(registerRequest);
                        break;
                    }
                    case "commonReg": {
                        CommonDeviceRequest request = JsonUtil.getEntity(content, CommonDeviceRequest.class);
                        deviceController.registerCommonDevice(request);
                        break;
                    }
                    case "register": {
                        DeviceRegisterRequest request = JsonUtil.getEntity(content, DeviceRegisterRequest.class);
                        deviceController.registerDevice(request);
                        break;
                    }
                    case "status": {
                        DeviceSituationRequest request = JsonUtil.getEntity(content, DeviceSituationRequest.class);
                        deviceController.status(request);
                        break;
                    }
                    case "event": {
                        DeviceEventRequest request = JsonUtil.getEntity(content, DeviceEventRequest.class);
                        deviceController.event(request);
                        break;
                    }
                    case "ping": {
                        DevicePingRequest request = JsonUtil.getEntity(content, DevicePingRequest.class);
                        deviceController.ping(request);
                        break;
                    }
                    case "rest": {
                        deviceController.reset(content);
                        break;
                    }
                    case "value": {
                        SensorValueRequest request = JsonUtil.getEntity(content, SensorValueRequest.class);
                        deviceController.getSensorValue(request);
                        break;
                    }
                    case "/pm25/register": {
                        DeviceRegisterRequest request = JsonUtil.getEntity(content, DeviceRegisterRequest.class);
                        deviceController.registerPM25(request);
                        break;
                    }
                    case "/door/register": {
                        CommonDeviceRequest request = JsonUtil.getEntity(content, CommonDeviceRequest.class);
                        deviceController.registerDoor(request);
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
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

package com.hifish.app.mqtt;

/**
 * Created with IntelliJ IDEA.
 * User: liji
 * Date: 16/7/11
 * Time: 下午2:15
 * To change this template use File | Settings | File Templates.
 * MQTT 配置
 */
public class MQTTConfig {

    private static String topic;

    private static int qos;

    private static String broker;

    private static String clientId;

    private static String userName;

    private static String password;

    private static String deviceWillTopic;

    private static String testTopic;


    private static String clientKey;

    private static String keyStore;

    /**
     * Gets client key.
     *
     * @return the client key
     */
    public static String getClientKey() {
        return clientKey;
    }

    /**
     * Sets client key.
     *
     * @param clientKey the client key
     */
    public static void setClientKey(String clientKey) {
        MQTTConfig.clientKey = clientKey;
    }

    /**
     * Gets key store.
     *
     * @return the key store
     */
    public static String getKeyStore() {
        return keyStore;
    }

    /**
     * Sets key store.
     *
     * @param keyStore the key store
     */
    public static void setKeyStore(String keyStore) {
        MQTTConfig.keyStore = keyStore;
    }

    /**
     * Gets test topic.
     *
     * @return the test topic
     */
    public static String getTestTopic() {
        return testTopic;
    }

    /**
     * Sets test topic.
     *
     * @param testTopic the test topic
     */
    public static void setTestTopic(String testTopic) {
        MQTTConfig.testTopic = testTopic;
    }

    /**
     * Gets device will topic.
     *
     * @return the device will topic
     */
    public static String getDeviceWillTopic() {
        return deviceWillTopic;
    }

    /**
     * Sets device will topic.
     *
     * @param deviceWillTopic the device will topic
     */
    public static void setDeviceWillTopic(String deviceWillTopic) {
        MQTTConfig.deviceWillTopic = deviceWillTopic;
    }

    /**
     * Gets topic.
     *
     * @return the topic
     */
    public static String getTopic() {
        return topic;
    }

    /**
     * Sets topic.
     *
     * @param topic the topic
     */
    public static void setTopic(String topic) {
        MQTTConfig.topic = topic;
    }

    /**
     * Gets qos.
     *
     * @return the qos
     */
    public static int getQos() {
        return qos;
    }

    /**
     * Sets qos.
     *
     * @param qos the qos
     */
    public static void setQos(int qos) {
        MQTTConfig.qos = qos;
    }

    /**
     * Gets broker.
     *
     * @return the broker
     */
    public static String getBroker() {
        return broker;
    }

    /**
     * Sets broker.
     *
     * @param broker the broker
     */
    public static void setBroker(String broker) {
        MQTTConfig.broker = broker;
    }

    /**
     * Gets client id.
     *
     * @return the client id
     */
    public static String getClientId() {
        return clientId;
    }

    /**
     * Sets client id.
     *
     * @param clientId the client id
     */
    public static void setClientId(String clientId) {
        MQTTConfig.clientId = clientId;
    }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public static String getUserName() {
        return userName;
    }

    /**
     * Sets user name.
     *
     * @param userName the user name
     */
    public static void setUserName(String userName) {
        MQTTConfig.userName = userName;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public static String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public static void setPassword(String password) {
        MQTTConfig.password = password;
    }
}

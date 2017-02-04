package com.hifish.app.entity;

import javax.persistence.Entity;


/**
 * The type Air device.
 */
@Entity
public class AirDeviceData extends BaseEntity {

    private int pm2_5;
    private int pm10;
    private double temperature;
    private double humidity;
    private String deviceId;

    /**
     * Gets pm 2 5.
     *
     * @return the pm 2 5
     */
    public int getPm2_5() {
        return pm2_5;
    }

    /**
     * Sets pm 2 5.
     *
     * @param pm2_5 the pm 2 5
     */
    public void setPm2_5(int pm2_5) {
        this.pm2_5 = pm2_5;
    }

    /**
     * Gets pm 10.
     *
     * @return the pm 10
     */
    public int getPm10() {
        return pm10;
    }

    /**
     * Sets pm 10.
     *
     * @param pm10 the pm 10
     */
    public void setPm10(int pm10) {
        this.pm10 = pm10;
    }

    /**
     * Gets temperature.
     *
     * @return the temperature
     */
    public double getTemperature() {
        return temperature;
    }

    /**
     * Sets temperature.
     *
     * @param temperature the temperature
     */
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    /**
     * Gets humidity.
     *
     * @return the humidity
     */
    public double getHumidity() {
        return humidity;
    }

    /**
     * Sets humidity.
     *
     * @param humidity the humidity
     */
    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    /**
     * Gets device id.
     *
     * @return the device id
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * Sets device id.
     *
     * @param deviceId the device id
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}

package com.hifish.app.domain;

/**
 * The type Air device data statistics.
 */
public class AirDeviceDataStatistics {

    private String createTime;

    private String deviceId;

    private int pm2_5Avg;

    private int pm2_5Max;

    private int pm2_5Min;

    /**
     * Gets create time.
     *
     * @return the create time
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * Sets create time.
     *
     * @param createTime the create time
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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

    /**
     * Gets pm 2 5 avg.
     *
     * @return the pm 2 5 avg
     */
    public int getPm2_5Avg() {
        return pm2_5Avg;
    }

    /**
     * Sets pm 2 5 avg.
     *
     * @param pm2_5Avg the pm 2 5 avg
     */
    public void setPm2_5Avg(int pm2_5Avg) {
        this.pm2_5Avg = pm2_5Avg;
    }

    /**
     * Gets pm 2 5 max.
     *
     * @return the pm 2 5 max
     */
    public int getPm2_5Max() {
        return pm2_5Max;
    }

    /**
     * Sets pm 2 5 max.
     *
     * @param pm2_5Max the pm 2 5 max
     */
    public void setPm2_5Max(int pm2_5Max) {
        this.pm2_5Max = pm2_5Max;
    }

    /**
     * Gets pm 2 5 min.
     *
     * @return the pm 2 5 min
     */
    public int getPm2_5Min() {
        return pm2_5Min;
    }

    /**
     * Sets pm 2 5 min.
     *
     * @param pm2_5Min the pm 2 5 min
     */
    public void setPm2_5Min(int pm2_5Min) {
        this.pm2_5Min = pm2_5Min;
    }
}
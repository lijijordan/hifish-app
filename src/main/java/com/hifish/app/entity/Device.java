package com.hifish.app.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;


/**
 * The type Air device.
 */
@Entity
public class Device extends BaseEntity {

    /**
     * sn
     */
    @Column(unique = true)
    private String deviceId;

    @ManyToMany(mappedBy = "devices", cascade = CascadeType.ALL)
    private List<UserInfo> users;

    private String deviceName;

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
     * Gets users.
     *
     * @return the users
     */
    public List<UserInfo> getUsers() {
        return users;
    }

    /**
     * Sets users.
     *
     * @param users the users
     */
    public void setUsers(List<UserInfo> users) {
        this.users = users;
    }

    /**
     * Gets device name.
     *
     * @return the device name
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * Sets device name.
     *
     * @param deviceName the device name
     */
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
}

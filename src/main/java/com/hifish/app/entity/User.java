package com.hifish.app.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Set;


/**
 * The type Air device.
 */
@Entity
public class User extends BaseEntity {

    /**
     * 微信ID
     */
    private String openId;
    private String name;

    @ManyToMany
    @JoinTable(
            name = "users_on_devices",
            joinColumns = @JoinColumn(name = "device_id", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "ID"))
    private Set<AirDevice> airDevices;

    /**
     * Gets open id.
     *
     * @return the open id
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * Sets open id.
     *
     * @param openId the open id
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets air devices.
     *
     * @return the air devices
     */
    public Set<AirDevice> getAirDevices() {
        return airDevices;
    }

    /**
     * Sets air devices.
     *
     * @param airDevices the air devices
     */
    public void setAirDevices(Set<AirDevice> airDevices) {
        this.airDevices = airDevices;
    }
}

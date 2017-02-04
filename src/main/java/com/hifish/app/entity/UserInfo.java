/**
 * Copyright © 2016 Seven Color. All rights reserved.
 *
 * @Description: 微信用户信息
 * @author: yinhong
 * @date: 2016年11月26日 上午11:16:17
 * @version: V1.0
 */
package com.hifish.app.entity;

import javax.persistence.*;
import java.util.List;

/**
 * The type User info.
 *
 * @Description: 用户信息
 */
@Entity
public class UserInfo extends BaseEntity {

    private String nickName;
    private Integer sex;
    private String city;
    private String province;
    private String country;
    private String headImageUrl;
    private Integer subscribe;
    private String language;
    private Long subscribeTime;
    private String remark;
    private Integer groupId;
    private String password;
    /**
     * 微信ID
     */
    @Column(name = "open_id", unique = true)
    private String openId;
    @ManyToMany
    @JoinTable(
            name = "users_on_devices",
            joinColumns = @JoinColumn(name = "device_id", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "ID"))
    private List<Device> devices;

    /**
     * Gets nick name.
     *
     * @return the nick name
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * Sets nick name.
     *
     * @param nickName the nick name
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * Gets sex.
     *
     * @return the sex
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * Sets sex.
     *
     * @param sex the sex
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets province.
     *
     * @return the province
     */
    public String getProvince() {
        return province;
    }

    /**
     * Sets province.
     *
     * @param province the province
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * Gets country.
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets country.
     *
     * @param country the country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets head image url.
     *
     * @return the head image url
     */
    public String getHeadImageUrl() {
        return headImageUrl;
    }

    /**
     * Sets head image url.
     *
     * @param headImageUrl the head image url
     */
    public void setHeadImageUrl(String headImageUrl) {
        this.headImageUrl = headImageUrl;
    }

    /**
     * Gets subscribe.
     *
     * @return the subscribe
     */
    public Integer getSubscribe() {
        return subscribe;
    }

    /**
     * Sets subscribe.
     *
     * @param subscribe the subscribe
     */
    public void setSubscribe(Integer subscribe) {
        this.subscribe = subscribe;
    }

    /**
     * Gets language.
     *
     * @return the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets language.
     *
     * @param language the language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Gets subscribe time.
     *
     * @return the subscribe time
     */
    public Long getSubscribeTime() {
        return subscribeTime;
    }

    /**
     * Sets subscribe time.
     *
     * @param subscribeTime the subscribe time
     */
    public void setSubscribeTime(Long subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    /**
     * Gets remark.
     *
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * Sets remark.
     *
     * @param remark the remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * Gets group id.
     *
     * @return the group id
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * Sets group id.
     *
     * @param groupId the group id
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

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
     * Gets devices.
     *
     * @return the devices
     */
    public List<Device> getDevices() {
        return devices;
    }

    /**
     * Sets devices.
     *
     * @param devices the devices
     */
    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }
}

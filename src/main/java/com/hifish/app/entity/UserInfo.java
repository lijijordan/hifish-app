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
import java.util.Set;

/**
 * @Description: 用户信息
 */
@Entity
public class UserInfo extends BaseEntity {

    private String nickName;
    private String sex;
    private String city;
    private String province;
    private String country;
    private String headImageUrl;
    private String subscribe;
    private String language;
    private String subscribeTime;
    private String remark;
    private String groupId;
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
    private Set<AirDeviceData> airDevices;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getHeadImageUrl() {
        return headImageUrl;
    }

    public void setHeadImageUrl(String headImageUrl) {
        this.headImageUrl = headImageUrl;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Set<AirDeviceData> getAirDevices() {
        return airDevices;
    }

    public void setAirDevices(Set<AirDeviceData> airDevices) {
        this.airDevices = airDevices;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickname) {
        this.nickName = nickname == null ? null : nickname.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(String subscribe) {
        this.subscribe = subscribe == null ? null : subscribe.trim();
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language == null ? null : language.trim();
    }

    public String getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(String subscribeTime) {
        this.subscribeTime = subscribeTime == null ? null : subscribeTime.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

}

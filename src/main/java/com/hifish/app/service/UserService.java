package com.hifish.app.service;

import com.hifish.app.dao.jpa.DeviceRepository;
import com.hifish.app.dao.jpa.UserRepository;
import com.hifish.app.entity.Device;
import com.hifish.app.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


/**
 * The type User service.
 */
@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    /**
     * Gets user by account.
     *
     * @param openId the open id
     * @return the user by account
     */
    public UserInfo getUserByAccount(String openId) {
        return this.userRepository.findByOpenId(openId);
    }

    /**
     * Add user.
     *
     * @param userInfo the wx user info
     */
    public void addUser(UserInfo userInfo) {
        // 已经绑定过微信账户
        UserInfo userInfo1 = this.userRepository.findByOpenId(userInfo.getOpenId());
        if (userInfo1 != null) { // 更新用户
            userInfo.setID(userInfo1.getID());
        }
        this.userRepository.save(userInfo);
    }

    /**
     * Delete user.
     *
     * @param openid the openid
     */
    public void deleteUser(String openid) {
        UserInfo userInfo = this.userRepository.findByOpenId(openid);
        if (userInfo != null) {
//            userInfo.setDel(true);
//            this.userRepository.save(userInfo);
            this.userRepository.delete(userInfo);
        }
    }

    /**
     * Bind device.
     *
     * @param openId   the open id
     * @param deviceId the device id
     */
    @Transactional
    public void bindDevice(String openId, String deviceId) {
        UserInfo userInfo = this.userRepository.findByOpenId(openId);
        if (userInfo != null) {
            Device device = this.deviceRepository.findByDeviceId(deviceId);
            if (device == null) { // 首次绑定设备
                device = new Device();
                device.setDeviceId(deviceId);
                device.setDeviceName("空气检测_" + deviceId);
                List<UserInfo> list = new ArrayList<>();
                list.add(userInfo);
                device.setUsers(list);
                this.deviceRepository.save(device);
                List<Device> devices;
                if (userInfo.getDevices() != null) {
                    devices = userInfo.getDevices();
                } else {
                    devices = new ArrayList<>();
                }
                devices.add(device);
                userInfo.setDevices(devices);
                this.userRepository.save(userInfo);
            }
        }
    }
}

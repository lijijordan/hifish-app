package com.hifish.app.service;

import com.hifish.app.dao.jpa.UserRepository;
import com.hifish.app.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * The type User service.
 */
@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

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
}

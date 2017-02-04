package com.hifish.app.dao.jpa;

import com.hifish.app.entity.UserInfo;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * The interface User repository.
 */
public interface UserRepository extends PagingAndSortingRepository<UserInfo, Long> {

    /**
     * Find by open id user info.
     *
     * @param openId the open id
     * @return the user info
     */
    UserInfo findByOpenId(String openId);
}
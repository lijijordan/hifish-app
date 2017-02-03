package com.hifish.app.dao.jpa;

import com.hifish.app.entity.UserInfo;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * The interface User repository.
 */
public interface UserRepository extends PagingAndSortingRepository<UserInfo, Long> {

    UserInfo findByOpenId(String openId);
}
package com.hifish.app.dao.jpa;

import com.hifish.app.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * The interface User repository.
 */
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

}
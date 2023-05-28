package com.rosariob.springboot.cruddemo.dao;

import com.rosariob.springboot.cruddemo.entity.User;

public interface UserDao {

    User findByUserName(String userName);

    User save(User user);
    
}

package com.rosariob.springboot.cruddemo.service;

import com.rosariob.springboot.cruddemo.dto.UserDto;
import com.rosariob.springboot.cruddemo.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

	User findByUserName(String userName);

	User registerNewUserAccount(UserDto accountDto);
}
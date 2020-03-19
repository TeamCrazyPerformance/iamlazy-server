package com.tcp.iamlazy.user.service;

import com.tcp.iamlazy.user.repository.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserMapper userMapper;

    public UserService(UserMapper userMapper){
        this.userMapper = userMapper;
    }


}

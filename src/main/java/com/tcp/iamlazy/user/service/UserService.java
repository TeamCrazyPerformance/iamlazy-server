package com.tcp.iamlazy.user.service;

import com.tcp.iamlazy.user.entity.User;
import com.tcp.iamlazy.user.repository.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    private final UserMapper userMapper;

    public UserService(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    /**
     * 사용자가 등록되었었는지를 확인합니다.
     * @param user
     * @return 기존에 존재할 경우 참값.
     */
    public boolean isUserExist(User user) {
        final User userExist = userMapper.isUserExist(user);

        log.info("User found result is : {}", userExist);

        if (userExist == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 사용자를 등록합니다.
     * @param user
     * @return 성공 유무
     */
    public boolean registerUser(User user) {
        final int insertCount = userMapper.insertUser(user);
        log.info("User inserted count affected : {}", insertCount);
        if (insertCount > 0) {
            return true;
        } else {
            return false;
        }
    }
}

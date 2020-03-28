package com.tcp.iamlazy.user.repository;

import com.tcp.iamlazy.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {

  User isUserExist(User user);

  int insertUser(User user);

}

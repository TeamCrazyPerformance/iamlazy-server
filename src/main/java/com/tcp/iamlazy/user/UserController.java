package com.tcp.iamlazy.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/review")
public class UserController {
    @Autowired
    UserService userService;

}

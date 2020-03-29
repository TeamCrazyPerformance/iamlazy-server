package com.tcp.iamlazy.user.controller;

import com.tcp.iamlazy.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.net.URI;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("사용자 Social 로그인 API ")
    @ApiParam(value = "type : 로그인 타입, kakao only supported.")
    @GetMapping(value = "/login", produces = "text/html")
    public ResponseEntity<String> loginUser(@RequestParam(required = false, name = "type", defaultValue = "default")String param) {

        if (param.equalsIgnoreCase("kakao")) {
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create("/oauth2/authorization/kakao"));
            return new ResponseEntity<>(headers, HttpStatus.FOUND);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("THE " + param + " login is not supported.");
        }
    }

}

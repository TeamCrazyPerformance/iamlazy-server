package com.tcp.iamlazy.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * For Kakao like SNS login oauth function this controller will handle all redirect urls.
 *
 * Created with intellij IDEA. by 2020 03 20/03/2020 3:00 오전 20 User we at 03 00 To change this
 * template use File | Settings | File Templates.
 */
@RestController
@Slf4j
public class OAuthController {

  @GetMapping("/login/success")
  public ResponseEntity<String> loginSuccess() {

    log.info("Login was returned but what to do next should i... the profile..?");

    return ResponseEntity.ok("OK Logged in");
  }
}

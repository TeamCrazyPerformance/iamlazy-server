package com.tcp.iamlazy.auth.controller;

import com.tcp.iamlazy.auth.model.KakaoAuthInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
//@RequestMapping("oauth_lazy")
public class OAuthController {

  @GetMapping("/oauth_lazy")
  public ResponseEntity<String> getKakaoUserAuthCode(@RequestParam("code")String code) {

    log.info("Code [{}] is returned", code);

    return ResponseEntity.ok("OK");
  }

  @GetMapping("/login/oauth2/code/kakao")
  public ResponseEntity<String> getKakaoUserAuthCode2(@RequestParam("code")String code) {

    log.info("2Code [{}] is returned", code);

    return ResponseEntity.ok("OK2");
  }
}

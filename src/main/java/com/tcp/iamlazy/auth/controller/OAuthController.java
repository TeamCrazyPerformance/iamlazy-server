package com.tcp.iamlazy.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcp.iamlazy.bean.pub.ApplicationEventPublishProvider;
import com.tcp.iamlazy.event.user.UserRegistrationEvent;
import com.tcp.iamlazy.user.model.KakaoPrincipal;
import com.tcp.iamlazy.util.func.Fn;
import io.vavr.control.Try;
import java.net.URI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
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

  private ObjectMapper objectMapper;

  public OAuthController() {
    this.objectMapper = new ObjectMapper();
  }

  @GetMapping("/login/success")
  public ResponseEntity<KakaoPrincipal> loginSuccess(OAuth2AuthenticationToken authentication,
                                             @AuthenticationPrincipal OAuth2User principal) {

    if (authentication == null) {
      log.info("There is no authentication");
      HttpHeaders headers = new HttpHeaders();
      headers.setLocation(URI.create("/login/fail"));
      return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    final Try<String> callTry = Try.of(() -> objectMapper.writeValueAsString(principal.getAttributes()));
    final String userSerial = callTry.getOrElseThrow(Fn.throwErr(callTry::getCause));

    final Try<KakaoPrincipal> accountTry = Try.of(() -> objectMapper.readValue(userSerial, KakaoPrincipal.class));
    final KakaoPrincipal kakaoAccount = accountTry.getOrElseThrow(Fn.throwErr(accountTry::getCause));

    ApplicationEventPublishProvider.publishEvent(new UserRegistrationEvent(kakaoAccount));

    log.info("Login was returned but what to do next should i... the profile..?");

    return ResponseEntity.ok(kakaoAccount);
  }

  @GetMapping("/login/fail")
  public ResponseEntity<String> loginFailuure() {

    log.info("You are not logged in.");

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body("You are not logged in");
  }

}

package com.tcp.iamlazy.auth.controller;

import java.io.IOException;
import java.net.URI;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
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

  private final OAuth2AuthorizedClientService authorizedClientService;

  public OAuthController(OAuth2AuthorizedClientService authorizedClientService) {
    this.authorizedClientService = authorizedClientService;
  }

  @GetMapping("/login/success")
  public ResponseEntity<String> loginSuccess(OAuth2AuthenticationToken authentication,
                                             @AuthenticationPrincipal OAuth2User principal) {

    if (authentication == null) {
      HttpHeaders headers = new HttpHeaders();
      headers.setLocation(URI.create("/login/fail"));
      return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    log.info("What is clientRegi id {}", authentication.getAuthorizedClientRegistrationId());
    log.info("What is name {}", authentication.getName());

    OAuth2AuthorizedClient client = authorizedClientService
        .loadAuthorizedClient(
          authentication.getAuthorizedClientRegistrationId(),
          authentication.getName()
        );

    log.info("Login was returned but what to do next should i... the profile..?");

    return ResponseEntity.ok("OK Logged in");
  }

  @GetMapping("/login/fail")
  public ResponseEntity<String> loginFailuure() {

    log.info("You are not logged in.");

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body("You are not logged in");
  }
}

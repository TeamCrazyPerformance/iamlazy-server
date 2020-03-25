package com.tcp.iamlazy.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with intellij IDEA. by 2020 03 26/03/2020 2:50 오전 26 User we at 02 50 To change this
 * template use File | Settings | File Templates.
 */
@RestController
public class TempController {


  @GetMapping("userinfo")
  public ResponseEntity<OAuth2User> getuserInfo(@AuthenticationPrincipal OAuth2User principal) {

    if ( principal == null ) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    return ResponseEntity.ok(principal);
  }

}

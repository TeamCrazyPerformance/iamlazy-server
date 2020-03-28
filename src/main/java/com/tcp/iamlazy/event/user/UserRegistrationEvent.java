package com.tcp.iamlazy.event.user;

import lombok.Data;
import lombok.ToString;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

/**
 *
 * 사용자 등록 이벤트 객체
 *
 * Created with intellij IDEA. by 2020 03 29/03/2020 1:21 오전 29 User we at 01 21 To change this
 * template use File | Settings | File Templates.
 */
@Data
@ToString
public class UserRegistrationEvent {

  private OAuth2AuthenticationToken authentication;
  private OAuth2User principal;

  public UserRegistrationEvent(OAuth2AuthenticationToken authentication, OAuth2User principal) {
    this.authentication = authentication;

    this.principal = principal;
  }
}

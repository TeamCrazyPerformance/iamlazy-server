package com.tcp.iamlazy.auth.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * 로그인 인증 후 접근 토큰과 갱신 토큰 등의 정보를 받기 위한 클래스.
 *
 * Created with intellij IDEA. by 2020 03 20/03/2020 3:15 오전 20 User we at 03 15 To change this
 * template use File | Settings | File Templates.
 */
@Getter
@Setter
@ToString
@JsonInclude(Include.NON_EMPTY)
public class KakaoAuthInfo {

  @JsonProperty("access_token")
  private String accessToken;
  @JsonProperty("token_type")
  private String tokenType;
  @JsonProperty("refresh_toekn")
  private String refreshToken;
  @JsonProperty("expires_in")
  private long expiresIn;
  @JsonProperty("scope")
  private String scope;

}

package com.tcp.iamlazy.auth.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Strings;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * 접근 토큰 갱신 시 성공 데이터를 받아올 클래스.
 *
 * Created with intellij IDEA. by 2020 03 20/03/2020 3:30 오전 20 User we at 03 30 To change this
 * template use File | Settings | File Templates.
 */
@Getter
@Setter
@ToString
@JsonInclude(Include.NON_EMPTY)
public class KakaoTokenRefreshResult {

  @JsonProperty("access_token")
  private String accessToken;
  @JsonProperty("token_type")
  private String tokenType;
  @JsonProperty("refreshToken")
  private String refreshToken;
  @JsonProperty("expires_in")
  private long expiresIn;

  /**
   * 갱신 토큰이 새롭게 응답으로 왔는지 여부를 확인합니다.
   * 거짓을 리턴한 경우, 토큰 값은 빈 문자열이 됩니다.
   * @return true if refresh_token was updated or false.
   */
  public boolean isRefreshTokenExist() {
    return Strings.isNullOrEmpty(getRefreshToken());
  }

  public String getRefreshToken() {
    return Strings.nullToEmpty(refreshToken);
  }
}

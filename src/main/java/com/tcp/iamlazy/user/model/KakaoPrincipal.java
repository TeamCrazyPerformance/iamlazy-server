package com.tcp.iamlazy.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Created with intellij IDEA. by 2020 03 29/03/2020 1:34 오전 29 User we at 01 34 To change this
 * template use File | Settings | File Templates.
 */
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode
public class KakaoPrincipal {

  @JsonProperty
  private String id;
  @JsonProperty
  private KakaoProperties properties;
  @JsonProperty("kakao_account")
  private KakaoAccount kakaoAccount;

  @JsonIgnore
  public String getUserName() {
    return this.properties.getNickname();
  }

  @JsonIgnore
  public String getUserImageLink() {
    return this.kakaoAccount.isProfileNeedsAgreement() ? "" : "";
  }
}

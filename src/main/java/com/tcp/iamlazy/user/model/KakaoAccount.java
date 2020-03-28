package com.tcp.iamlazy.user.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created with intellij IDEA. by 2020 03 29/03/2020 1:36 오전 29 User we at 01 36 To change this
 * template use File | Settings | File Templates.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoAccount {

  @JsonProperty("profile_needs_agreement")
  private boolean profileNeedsAgreement;
}

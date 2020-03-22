package com.tcp.iamlazy.auth.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * 응답 코드가 200이 아닌 경우에 에러를 받아낼 클래스.
 *
 * Created with intellij IDEA. by 2020 03 20/03/2020 3:34 오전 20 User we at 03 34 To change this
 * template use File | Settings | File Templates.
 */
@Getter
@Setter
@ToString
@JsonInclude(Include.NON_EMPTY)
public class KakaoErrorResponse {
  @JsonProperty("error")
  private String error;
  @JsonProperty("error_description")
  private String errorDescription;
}

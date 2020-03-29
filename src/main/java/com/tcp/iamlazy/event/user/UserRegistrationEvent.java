package com.tcp.iamlazy.event.user;

import com.tcp.iamlazy.user.model.KakaoPrincipal;
import lombok.Data;
import lombok.ToString;

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
  private final KakaoPrincipal kakaoPrincipal;
}

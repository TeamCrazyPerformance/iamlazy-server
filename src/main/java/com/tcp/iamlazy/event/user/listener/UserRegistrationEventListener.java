package com.tcp.iamlazy.event.user.listener;

import com.tcp.iamlazy.event.user.UserRegistrationEvent;
import com.tcp.iamlazy.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * 사용자 등록에 대한 처리를 진행한다.
 * 만일 등록이 되어 있다면 아무런 처리도 하지 않는다.
 *
 * Created with intellij IDEA. by 2020 03 29/03/2020 1:23 오전 29 User we at 01 23 To change this
 * template use File | Settings | File Templates.
 */
@Slf4j
@Component
@Deprecated
public class UserRegistrationEventListener {

  private final UserService userService;

  public UserRegistrationEventListener(UserService userService) {
    this.userService = userService;
  }

  /**
   * @EventListener(condition = "#event.kinds.name() == 'NORMAL'")
   * 와 같이 객체에 대한 표현식을 사용하여 이벤트 호출 함수를 조절할 수 있다.
   * @param event
   */
  @EventListener
  @Transactional
  public void handleUserRegistrationEvent(UserRegistrationEvent event) {
    log.info("User registration event was {}", event);
  }

}

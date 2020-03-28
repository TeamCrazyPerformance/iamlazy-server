package com.tcp.iamlazy.event.user.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcp.iamlazy.event.user.UserRegistrationEvent;
import com.tcp.iamlazy.user.entity.User;
import com.tcp.iamlazy.user.model.KakaoPrincipal;
import com.tcp.iamlazy.user.service.UserService;
import com.tcp.iamlazy.util.func.Fn;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

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
public class UserRegistrationEventListener {

  private final UserService userService;
  private final ObjectMapper objectMapper;

  public UserRegistrationEventListener(UserService userService) {
    this.userService = userService;
    objectMapper = new ObjectMapper();
  }

  /**
   * @EventListener(condition = "#event.kinds.name() == 'NORMAL'")
   * 와 같이 객체에 대한 표현식을 사용하여 이벤트 호출 함수를 조절할 수 있다.
   * @param event
   */
  @EventListener
  public void handleUserRegistrationEvent(UserRegistrationEvent event) {

    log.info("User registration event was {}", event);

    final OAuth2User userInfo = event.getPrincipal();

    final Try<String> callTry = Try.of(() -> objectMapper.writeValueAsString(userInfo.getAttributes()));
    final String userSerial = callTry.getOrElseThrow(Fn.throwErr(callTry::getCause));

    final Try<KakaoPrincipal> accountTry = Try.of(() -> objectMapper.readValue(userSerial, KakaoPrincipal.class));
    final KakaoPrincipal kakaoAccount = accountTry.getOrElseThrow(Fn.throwErr(accountTry::getCause));

    final User loginUser = retrieveUser(kakaoAccount);

    final boolean userExist = userService.isUserExist(loginUser);
    if (!userExist) {
      if (!userService.registerUser(loginUser)) {
        log.error("Register failed");
        throw new RuntimeException("user register failed");
      }
    }


  }

  protected User retrieveUser(KakaoPrincipal kakaoAccount) {
    User user = new User();
    user.setUserKakaoID(kakaoAccount.getId());
    user.setUserImage(kakaoAccount.getUserImageLink());
    user.setUserName(kakaoAccount.getUserName());

    return user;
  }

}

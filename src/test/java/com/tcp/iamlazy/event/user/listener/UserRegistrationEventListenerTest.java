package com.tcp.iamlazy.event.user.listener;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcp.iamlazy.event.user.UserRegistrationEvent;
import com.tcp.iamlazy.user.entity.User;
import com.tcp.iamlazy.user.model.KakaoAccount;
import com.tcp.iamlazy.user.model.KakaoPrincipal;
import com.tcp.iamlazy.user.model.KakaoProperties;
import com.tcp.iamlazy.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;



/**
 * Created with intellij IDEA. by 2020 03 29/03/2020 2:59 오전 29 User we at 02 59 To change this
 * template use File | Settings | File Templates.
 */
@RunWith(MockitoJUnitRunner.class)
@Slf4j
public class UserRegistrationEventListenerTest {

  @Mock
  private UserService userServiceMock;
  @Mock
  private KakaoPrincipal kakaoPrincipal;
  private UserRegistrationEventListener listenerSpy;
  private User user;
  private UserRegistrationEvent event;

  @Before
  public void beforeEach() {
    MockitoAnnotations.initMocks(this);
    listenerSpy = spy(new UserRegistrationEventListener(userServiceMock));
    user = new User();
    user.setUserName("username");
    user.setUserImage("");
    user.setUserKakaoID("kakaoId");
//    user.setUserIdx(323412);

    event = new UserRegistrationEvent(kakaoPrincipal);
  }

  @Test
  public void whenUserHasNotBeenRegisteredInsertShouldBeCalled() throws Exception {
    when(userServiceMock.isUserExist(user)).thenReturn(false);
    when(userServiceMock.registerUser(user)).thenReturn(true);
    doReturn(user.getUserName()).when(kakaoPrincipal).getUserName();
    doReturn(user.getUserImage()).when(kakaoPrincipal).getUserImageLink();
    doReturn(user.getUserKakaoID()).when(kakaoPrincipal).getId();

    listenerSpy.handleUserRegistrationEvent(event);

    verify(userServiceMock, times(1)).registerUser(user);

  }

  @Test
  public void whenUserHasBeenRegisteredInsertShouldNotBeCalled() throws Exception {
    when(userServiceMock.isUserExist(user)).thenReturn(true);
    doReturn(user.getUserName()).when(kakaoPrincipal).getUserName();
    doReturn(user.getUserImage()).when(kakaoPrincipal).getUserImageLink();
    doReturn(user.getUserKakaoID()).when(kakaoPrincipal).getId();

    listenerSpy.handleUserRegistrationEvent(event);

    verify(userServiceMock, never()).registerUser(user);
  }

  @Test
  public void generatedKAkao() throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    KakaoPrincipal kakaoPrincipal = new KakaoPrincipal();
    kakaoPrincipal.setId("39392932");
    KakaoProperties p = new KakaoProperties();
    p.setNickname("nickname");
    KakaoAccount a = new KakaoAccount();
    a.setProfileNeedsAgreement(false);
    kakaoPrincipal.setKakaoAccount(a);
    kakaoPrincipal.setProperties(p);
    final String s = mapper.writeValueAsString(kakaoPrincipal);
    System.out.println(s);
  }
}

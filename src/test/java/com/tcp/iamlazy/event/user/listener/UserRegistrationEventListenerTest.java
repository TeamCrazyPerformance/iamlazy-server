package com.tcp.iamlazy.event.user.listener;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.doReturn;

import com.tcp.iamlazy.event.user.UserRegistrationEvent;
import com.tcp.iamlazy.user.entity.User;
import com.tcp.iamlazy.user.model.KakaoPrincipal;
import com.tcp.iamlazy.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

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
  private OAuth2AuthenticationToken authentication;
  @Mock
  private OAuth2User principal;
  private UserRegistrationEventListener listenerSpy;
  private User user;

  @Before
  public void beforeEach() {
    MockitoAnnotations.initMocks(this);
    listenerSpy = spy(new UserRegistrationEventListener(userServiceMock));
    user = new User();
    user.setUserName("username");
    user.setUserImage("");
    user.setUserKakaoID("kakaoId");
    user.setUserIdx(323412);
  }

  @Test
  public void whenUserHaveNotBeenRegisteredInsertShouldBeCalled() throws Exception {



    when(userServiceMock.isUserExist(user)).thenReturn(false);
    when(userServiceMock.registerUser(user)).thenReturn(true);
//    when(ReflectionTestUtils.invokeMethod(listenerSpy, "retrieveUser", any(KakaoPrincipal.class))).thenReturn(user);
//    doReturn(user).when(ReflectionTestUtils.invokeMethod(listenerSpy, "retrieveUser", any(
//        KakaoPrincipal.class)));
//    doReturn(user).when(listenerSpy).retrieveUser(any(KakaoPrincipal.class));
    doReturn(user).when(listenerSpy, "retrieveUser", any(KakaoPrincipal.class));
//    when(Whitebox.invokeMethod(listenerSpy, "retrieveUser", any(KakaoPrincipal.class))).thenReturn(user);

    UserRegistrationEvent event = new UserRegistrationEvent(authentication, principal);
    listenerSpy.handleUserRegistrationEvent(event);

    verify(userServiceMock, times(1)).registerUser(user);

  }

}

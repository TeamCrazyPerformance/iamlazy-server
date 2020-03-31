package com.tcp.iamlazy.event.user.listener;

import com.tcp.iamlazy.event.user.UserRegistrationEvent;
import com.tcp.iamlazy.user.entity.User;
import com.tcp.iamlazy.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;



/**
 * Created with intellij IDEA. by 2020 03 29/03/2020 2:59 오전 29 User we at 02 59 To change this
 * template use File | Settings | File Templates.
 */
@RunWith(MockitoJUnitRunner.class)
@Slf4j
@Deprecated
/* @fixme : this test should be changed or removed. */
public class UserRegistrationEventListenerTest {

  @Mock
  private UserService userServiceMock;
  private UserRegistrationEventListener listenerSpy;
  private User user;
  private UserRegistrationEvent event;

}

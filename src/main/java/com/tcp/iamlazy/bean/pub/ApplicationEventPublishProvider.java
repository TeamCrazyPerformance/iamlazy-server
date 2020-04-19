package com.tcp.iamlazy.bean.pub;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * Created with intellij IDEA. by 2020 03 29/03/2020 1:19 오전 29 User we at 01 19 To change this
 * template use File | Settings | File Templates.
 */
@Slf4j
@Component
public class ApplicationEventPublishProvider implements ApplicationEventPublisherAware {

  private static ApplicationEventPublisher publisher;

  public static ApplicationEventPublisher getEventPublisher() {
    return publisher;
  }

  public static void publishEvent(Object event) {
    if (publisher != null) {
      publisher.publishEvent(event);
    } else {
      log.warn("ApplicationEventPublisher가 존재하지 않습니다.");
    }
  }

  @Override
  public void setApplicationEventPublisher(ApplicationEventPublisher eventPublisher) {
    ApplicationEventPublishProvider.publisher = eventPublisher;
  }
}

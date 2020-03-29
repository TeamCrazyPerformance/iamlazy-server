package com.tcp.iamlazy.util.func;

import com.tcp.iamlazy.util.functor.ToFunctor;
import java.util.function.BiFunction;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

/**
 *
 * 인증된 사용자 정보 가져오는 것을 편하게 하기 위한 펑터.
 *
 * Created with intellij IDEA. by 2020 03 29/03/2020 2:31 오후 29 User we at 14 31 To change this
 * template use File | Settings | File Templates.
 */
public class AuthFn {

  public static ToFunctor<BiFunction<String, String, OAuth2AuthorizedClient>, OAuth2AuthorizedClient> from(
      OAuth2AuthenticationToken o) {

    return (bi) -> bi.apply(o.getAuthorizedClientRegistrationId(), o.getName());

  };

}

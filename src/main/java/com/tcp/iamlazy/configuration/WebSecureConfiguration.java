package com.tcp.iamlazy.configuration;

import com.tcp.iamlazy.auth.role.RoleType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

/**
 *
 * Too see auto configuration in detaiil {@see OAuth2ClientAutoConfiguration} class.
 *
 * Created with intellij IDEA. by 2020 03 12/03/2020 7:28 오후 12 User we at 19 28 To change this
 * template use File | Settings | File Templates.
 */
@Configuration
@Slf4j
public class WebSecureConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.authorizeRequests()
        .antMatchers("/", "/login/**", "/*.html", "/user/login")
        .permitAll()
        .and()
        .authorizeRequests()
        .antMatchers("/console/**")
        .permitAll()
        .and()
        .authorizeRequests()
        .anyRequest().hasAuthority(RoleType.USER.getRoleName())
        ;

    httpSecurity.
        csrf()
        .disable()
        ;

    httpSecurity
        .headers()
        .frameOptions()
        .disable();

    httpSecurity
        .exceptionHandling(e -> e.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
    ;

    httpSecurity
        .oauth2Login()
        .userInfoEndpoint().userService(new DefaultOAuth2UserService())
        .and()
        .defaultSuccessUrl("/login/success")
        .failureUrl("/login/fail")
    ;

  }

}

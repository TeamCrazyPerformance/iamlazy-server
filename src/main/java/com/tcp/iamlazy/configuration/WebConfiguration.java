package com.tcp.iamlazy.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

/**
 * Created with intellij IDEA. by 2020 03 12/03/2020 7:28 오후 12 User we at 19 28 To change this
 * template use File | Settings | File Templates.
 */
@Configuration
@Slf4j
public class WebConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.authorizeRequests().antMatchers("/", "/login").permitAll().and()
        .authorizeRequests().antMatchers("/console/**").permitAll()
    ;
    httpSecurity.authorizeRequests().anyRequest().authenticated();
    httpSecurity.csrf().disable();
    httpSecurity.headers().frameOptions().disable();
    httpSecurity
        .exceptionHandling(e -> e.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
    );
    httpSecurity.oauth2Login()
        .defaultSuccessUrl("/login/success");

  }

}

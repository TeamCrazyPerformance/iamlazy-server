package com.tcp.iamlazy.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created with intellij IDEA. by 2020 03 12/03/2020 7:28 오후 12 User we at 19 28 To change this
 * template use File | Settings | File Templates.
 */
@Configuration
public class WebConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.authorizeRequests().antMatchers("/").permitAll().and()
        .authorizeRequests().antMatchers("/console/**").permitAll();
    httpSecurity.csrf().disable();
    httpSecurity.headers().frameOptions().disable();
  }
}

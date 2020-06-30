package com.vcjmhg.tacocloud.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author vcjmhg
 * @version
 * ch3
 * @description
 * SpringBoot Security相关的一些配置
 * @date 2020/6/30 21:39
 **/
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.inMemoryAuthentication().withUser("vcjmhg")
               .password("{noop}123456")
               .authorities("ROLE_USER")
               .and()
               .withUser("zhaojianheng")
               .password("{noop}123456")
               .authorities("ROLE_USER");
    }
}

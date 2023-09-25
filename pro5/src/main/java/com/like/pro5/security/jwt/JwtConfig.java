package com.like.pro5.security.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 직접 만든 JwtTokenProvider 와 JwtFilter 를 SecurityConfig 에 적용 할 때 사용
 * 즉, 직접 만든 JwtFilter 를 Security Filter 앞에 끼워 넣는다.
 */
@RequiredArgsConstructor
public class JwtConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        JwtFilter customFilter = new JwtFilter(jwtTokenProvider);
        httpSecurity.addFilterBefore(
                customFilter, UsernamePasswordAuthenticationFilter.class
        );
    }
}


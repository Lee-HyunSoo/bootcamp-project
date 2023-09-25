package com.like.pro5.security.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 사용자 정보가 잘못 되었거나, 토큰이 유효하지 않은 경우 호출
 */
@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.info("[commence] 인증 실패로 인한 response.sendError");
        /* 유효한 토큰을 제공하지 않고 접근하려 할 때 401 Unauthorized */
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED); // 401
    }
}

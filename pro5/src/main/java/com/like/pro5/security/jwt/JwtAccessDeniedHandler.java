package com.like.pro5.security.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 권한 없이 특정 페이지에 접근하려 할 때 호출
 * ex) USER 권한인 사람이 ADMIN 권한 페이지에 접근
 */
@Slf4j
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.info("[handle] 권한 없는 접근 발생");
        /* 필요한 권한 없이 접근하려 할 때 403 FORBIDDEN */
        response.sendError(HttpServletResponse.SC_FORBIDDEN);
    }
}

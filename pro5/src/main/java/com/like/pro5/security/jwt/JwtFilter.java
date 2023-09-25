package com.like.pro5.security.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * 실제 필터링 로직 수행
     *
     * 가입 / 로그인 / 재발급을 제외한 모든 request 요청은 해당 필터를 거친다.
     * 때문에 토큰 정보가 없거나 유효하지 않으면 정상적으로 동작하지 않는다.
     * 즉, 요청이 정상적으로 Controller 에 도착했다면 User 가 존재하는 것이 보장된다.
     * 주의할 점은, 직접 DB 를 조회한 것이 아니라 Access Token 내의 User Id를 꺼낸 것
     * 이기 때문에 탈퇴로 인해 User Id 가 없는 등 예외 상황은 Service 단에서 고려해야한다.
     *
     * 이후, JWT 토큰의 인증 정보를 현재 Thread 의 SecurityContext 내에 저장한다.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        /* Request Header 에서 토큰을 받아온다. */
        String token = resolveToken(request);
        log.info("[doFilterInternal] Token 값 추출 완료, Token : {}", token);

        /* JwtTokenProvider Class 내 validationToken Method 를 통해 토큰 유효성 검사 */
        /* 정상 토큰이면 해당 토큰을 통해 Authentication 을 가져와 SecurityContext 에 저장 */
        log.info("[doFilterInternal] Token 값 유효성 체크 시작");
        if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        log.info("[doFilterInternal] Token 값 유효성 체크 완료");
        filterChain.doFilter(request, response);
    }

    /**
     * Request Header 내 토큰 정보 추출
     */
    private String resolveToken(HttpServletRequest request) {
        log.info("[resolveToken] HTTP Header 내 Token 값 추출");
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);

        /* 추출한 토큰이 null 이 아니고 BEARER_PREFIX 가 포함되어 있다면 */
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.split(" ")[1].trim();
        }
        return null;
    }
}

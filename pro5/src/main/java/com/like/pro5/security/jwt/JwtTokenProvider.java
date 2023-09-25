package com.like.pro5.security.jwt;

import com.like.pro5.security.custom.CustomUserDetailsService;
import com.like.pro5.security.dto.TokenDto;
import com.like.pro5.util.trace.TraceStatus;
import com.like.pro5.util.trace.log.ThreadLocalLogTrace;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * 유저 정보를 통해 JWT 토큰 생성하거나 토큰을 바탕으로 유저 정보를 가져온다.
 */
@Slf4j
@Component
public class JwtTokenProvider {

    private static final String AUTHORITIES_KEY = "auth";
    private static final String BEARER_TYPE = "Bearer";
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30; // 30분
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7; // 7일
    private final Key key;
    private final ThreadLocalLogTrace trace;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * application.properties 에 정의 된 jwt.secret 값을 가져와
     * JWT 를 만들 때 사용하는 암호화 키 값을 생성
     */
    public JwtTokenProvider(@Value(value = "${jwt.secret}") String key, ThreadLocalLogTrace trace) {
        byte[] keyBytes = Decoders.BASE64.decode(key);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.trace = trace;
    }

    /**
     * 유저 정보를 받아 Access Token 및 Refresh Token 생성
     * authentication.getName() 을 통해 유저 이름을 가져온다. (USER PK)
     * Access Token : 유저와 권한 정보를 담는다.
     * Refresh Token : 특별한 정보 없이 만료일자만 담는다.
     */
    public TokenDto generateTokenDto(Authentication authentication) {
        log.info("[generateTokenDto] 토큰 생성 시작");
        /* Enum 에 선언한 권한 가져오기 */
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();

        /* Access Token 생성 */
        log.info("[generateTokenDto] Access Token 생성");
        Date accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        String accessToken = Jwts.builder()
                /* Authentication 의 부모인 Principal 내의 메서드 */
                .setSubject(authentication.getName())
                /* claim : key, value 쌍으로 이루어진 정보 [auth, USER] */
                .claim(AUTHORITIES_KEY, authorities)
                /* 토큰 유효기간 설정 */
                .setExpiration(accessTokenExpiresIn)
                /* 서명 키를 명세, HS512 알고리즘 사용 */
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
        log.info("[generateTokenDto] Access Token 생성 완료, AccessToken : {}", accessToken);

        /* Refresh Token 생성 */
        log.info("[generateTokenDto] Refresh Token 생성");
        String refreshToken = Jwts.builder()
                .setExpiration(new Date(now + REFRESH_TOKEN_EXPIRE_TIME))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        log.info("[generateTokenDto] 토큰 생성 완료, Refresh Token : {}", refreshToken);
        return TokenDto.builder()
                .grantType(BEARER_TYPE)
                .accessToken(accessToken)
                .accessTokenExpiresIn(accessTokenExpiresIn.getTime())
                .refreshToken(refreshToken)
                .build();
    }

    /**
     * JWT 토큰 복호화, 토큰에 들어있는 정보를 꺼낸다.
     * Access Token 에 유저 정보를 담기 때문에 accessToken 을 파라미터로 받는다.
     * CustomUserDetails 객체를 생성해 UsernamePasswordAuthenticationToken 형태로 return
     * -> SecurityContext 를 사용하기 위해
     */
    public Authentication getAuthentication(String accessToken) {
        TraceStatus status = trace.begin("JwtTokenProvider.getAuthentication()");
        /* 토큰 복호화 */
        Claims claims = parseClaims(accessToken);

        if (claims.get(AUTHORITIES_KEY) == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        /**
         * 해당 방식은 인증 객체를 저장하는 과정에서 DB 에 접근해 User 정보를 가져온게 아니라,
         * 토큰에서 추출한 정보로 인증 객체를 만드는 방식이다.
         * 때문에 @AuthenticationPrincipal 의 동작 순서와 맞지 않아 에러가 난다.
         */
//        /* Claim 에서 권한 정보 가져오기 */
//        /* 기존 return 값은 List 지만 상위 Collection 으로 return  */
//        Collection<? extends GrantedAuthority> authorities = Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
//
//        /* CustomUserDetails 객체를 만들어 Authentication return */
//        log.info("[getAuthentication] 토큰 인증 정보 조회 시작");
//        UserDetails principal = new User(claims.getSubject(), "", authorities);
//        log.info("[getAuthentication] 토큰 인증 정보 조회 완료");
//        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
        UserDetails userDetails = userDetailsService.loadUserByUsername(claims.getSubject());
        log.info("[getAuthentication] userDetails : {}", userDetails.getUsername());
        trace.end(status);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    /**
     * 토큰 정보를 검증
     */
    public boolean validateToken(String token) {
        log.info("[validateToken] 토큰 검증 시작");
        try {
            /* Jwts Module 을 사용한 토큰 검증 */
            Jwts.parserBuilder()
                    .setSigningKey(key).build()
                    .parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        log.info("[validateToken] 토큰 검증 완료");
        return false;
    }

    /**
     * 토큰 복호화
     */
    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }

    }
}

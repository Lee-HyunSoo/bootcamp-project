package com.like.pro5.service;

import com.like.pro5.controller.dto.UserDto;
import com.like.pro5.domain.entity.User;
import com.like.pro5.domain.repository.UserRepository;
import com.like.pro5.security.dto.TokenDto;
import com.like.pro5.security.entity.RefreshToken;
import com.like.pro5.security.jwt.JwtTokenProvider;
import com.like.pro5.security.repository.RefreshTokenRepository;
import com.like.pro5.util.trace.TraceStatus;
import com.like.pro5.util.trace.log.ThreadLocalLogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class AuthService {


    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final ThreadLocalLogTrace trace;

    @Transactional
    public UserDto join(UserDto userDto) {
        TraceStatus status = trace.begin("AuthService.join()");
        /* 이미 있는 회원인지 검증 */
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = User.createUser(userDto);
        userRepository.save(user);

        trace.end(status);
        return userDto;
    }

    @Transactional
    public TokenDto login(UserDto userDto) {
        TraceStatus status = trace.begin("AuthService.login()");
        /* id, password 를 기반으로 AuthenticationToken 생성 */
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword());

        log.info("[authenticationToken] : {}", authenticationToken);
        /* 실제 검증 (비밀번호 체크) 이 이루어진다. */
        /* authenticate 메서드가 실행 될 때, CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드 실행 */
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        log.info("[authentication] : {}", authentication.getName());
        /* 인증 정보를 기반으로 JWT 토큰 생성 */
        TokenDto tokenDto = jwtTokenProvider.generateTokenDto(authentication);
        log.info("[TokenDto] : {}", tokenDto);

        /* Refresh Token 저장 */
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);
        trace.end(status);
        /* 토큰 발급 */
        return tokenDto;
    }

    @Transactional
    public TokenDto reIssue(TokenDto tokenDto) {
        /* Refresh Token 검증 */
        if (!jwtTokenProvider.validateToken(tokenDto.getRefreshToken())) {
            throw new RuntimeException("Refresh Token 이 유효하지 않습니다.");
        }

        /* Access Token 에서 User Id 를 가져온다. */
        Authentication authentication = jwtTokenProvider.getAuthentication(tokenDto.getAccessToken());
        log.info("[reIssue] authentication : {}", authentication);
        log.info("[reIssue] authentication.getName() : {}", authentication.getName());
        /* 저장소에서 User Id 를 기반으로 Refresh Token 값을 가져온다. */
        RefreshToken refreshToken = refreshTokenRepository.findById(authentication.getName()).orElseThrow(RuntimeException::new);

        /* Refresh Token 이 일치하는지 검사 */
        if (!refreshToken.getValue().equals(tokenDto.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        /* 새로운 토큰 생성 */
        TokenDto newToken = jwtTokenProvider.generateTokenDto(authentication);

        /* 저장소 정보 업데이트 */
        RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        /* 토큰 발급 */
        return newToken;
    }

}

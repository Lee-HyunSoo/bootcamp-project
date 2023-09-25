package com.like.pro5.security.config;

import com.like.pro5.domain.repository.UserRepository;
import com.like.pro5.security.custom.CustomUserDetailsService;
import com.like.pro5.security.jwt.JwtAccessDeniedHandler;
import com.like.pro5.security.jwt.JwtAuthenticationEntryPoint;
import com.like.pro5.util.trace.log.ThreadLocalLogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    AuthenticationManager authenticationManager(
//            AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }

//    /** CORS 설정 */
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration config = new CorsConfiguration();
//
//        config.setAllowCredentials(true);
//        config.setAllowedOrigins(List.of("http://localhost:8080"));
//        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
//        config.setAllowedHeaders(List.of("*"));
//        config.setExposedHeaders(List.of("*"));
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", config);
//        return source;
//    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .cors()
//                  .and()
//                /* Cross Site Request Forgery 설정 disable */
//                /* Rest API 를 이용한 서버는 session 인증 기반이 아니기 때문에 끈다. */
//                .csrf().disable()
//                .formLogin().disable()
//                .httpBasic().disable()
//                /* 세션 사용 x */
//                .sessionManagement()
//                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                    .and()
//                /* 유효한 자격 증명 x */
//                .exceptionHandling()
//                    .authenticationEntryPoint(jwtAuthenticationEntryPoint)
//                    .accessDeniedHandler(jwtAccessDeniedHandler)
//                    .and()
//                .authorizeRequests()
////                    .antMatchers("/auth/**").permitAll()
//                    .antMatchers("/**").permitAll()
//                    .anyRequest().authenticated()
//                    .and()
//                .logout()
//                    .permitAll();
//
//        return httpSecurity.build();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests()
                /* 해당 경로로의 접근은 인증 절차 없이 허용 */
                    .antMatchers("/**").permitAll()
//                    .antMatchers("/admin/**").access("hasRole('ADMIN')")
                    /* antMatchers 외 모든 경로, 인증 필요 */
                    .anyRequest().permitAll()
                    .and()
                .formLogin()
                /* 기본 로그인 페이지 경로 */
                    .loginPage("/auth/login")
                    .loginProcessingUrl("/auth/login")
                    .defaultSuccessUrl("/")
                    .and()
                .logout()
                    .permitAll()
                    /* 로그아웃 url */
                    .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"))
                    /* 로그아웃 시 JSESSIONID 제거 */
                    .deleteCookies("JSESSIONID")
                    /* 로그아웃 시 세션 종료 */
                    .invalidateHttpSession(true)
                    /* 로그아웃 시 권한 제거 */
                    .clearAuthentication(true);

        return httpSecurity.build();
    }

}

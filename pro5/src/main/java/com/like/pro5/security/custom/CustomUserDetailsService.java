package com.like.pro5.security.custom;

import com.like.pro5.domain.entity.User;
import com.like.pro5.domain.repository.UserRepository;
import com.like.pro5.util.trace.TraceStatus;
import com.like.pro5.util.trace.log.ThreadLocalLogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ThreadLocalLogTrace trace;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TraceStatus status = trace.begin("CustomUserDetailsService.loadUserByUsername()");
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("DB 에 없는 유저입니다."));
        trace.end(status);
        return createCustomUserDetails(user);
    }

    /**
     * DB 에 User 가 존재한다면 CustomUserDetails 객체로 만들어서 return
     */
    private CustomUserDetails createCustomUserDetails(User user) {
        return CustomUserDetails.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }

}

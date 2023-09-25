package com.like.pro5.security.custom;

import com.like.pro5.domain.entity.Role;
import com.like.pro5.domain.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@NoArgsConstructor(force = true)
public class CustomUserDetails implements UserDetails {

    // UserDetails 내 기본 필드
    private String username;
    private String password;
    private Role role;
//    private boolean accountNonExpired; // 사용자 계정 만료 여부
//    private boolean accountNonLocked; // 계정 잠금 여부
//    private boolean credentialsNonExpired; // 비밀번호 만료 여부
//    private boolean enable; // 사용자 계정 활성화 여부
//    private Collection<? extends GrantedAuthority> authorities; // 사용자 권한 목록

    @Builder
    public CustomUserDetails(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

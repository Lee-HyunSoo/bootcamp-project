package com.like.pro5.security.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RefreshToken {

    @Id
    @Column(name = "refresh_token_id")
    private String key;

    @Column(name = "refresh_token_value")
    private String value;

    @Builder
    public RefreshToken(String key, String value) {
        this.key = key;
        this.value = value;
    }

    // == 비즈니스 로직 ==
    public RefreshToken updateValue(String token) {
        this.value = token;
        return this;
    }
}

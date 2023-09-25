package com.like.pro5.util.trace;

import lombok.Getter;

import java.util.UUID;

/**
 * 트랜잭션 ID 와 깊이를 표현하는 정보를 가진 클래스
 */
@Getter
public class TraceId {

    private String id;
    private int level;

    public TraceId() {
        this.id = createId();
        this.level = 0;
    }

    /**
     * 해당 필드에서만 사용하기 위한 private 생성자
     */
    private TraceId(String id, int level) {
        this.id = id;
        this.level = level;
    }

    /**
     *  UUID 를 이용한 id 생성
     */
    private String createId() {
        /* 생성한 UUID 가 너무 길기 때문에 8자리만 잘라 사용 */
        /* 생성 된 UUID 예시 : ab99e16f-3cde-4d24-8241-256108c203a2  */
        /* 이 중 ab99e16f 만 사용 */
        return UUID.randomUUID().toString().substring(0, 8);
    }

    /**
     * level 을 한 단계씩 올려 출력하기 위해
     */
    public TraceId createNextId() {
        return new TraceId(id, level + 1);
    }

    /**
     * level 을 한 단계씩 줄여 출력하기 위해
     */
    public TraceId createPreviousId() {
        return new TraceId(id, level - 1);
    }

    /**
     * 첫번째 level 인지 알 수 있는 메서드
     */
    public boolean isFirstLevel() {
        return level == 0;
    }

}
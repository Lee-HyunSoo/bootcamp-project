package com.like.pro5.util.trace;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 로그의 상태 정보를 나타내는 클래스
 */
@Getter
@AllArgsConstructor
public class TraceStatus {

    private TraceId traceId; // 트랜잭션 ID, level
    private Long startTimeMs; // 로그 시작시간
    private String message; // 로그 시작 시 메세지, 종료 시 메세지
}

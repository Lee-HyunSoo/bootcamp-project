package com.like.pro5.util.trace.log;

import com.like.pro5.util.trace.TraceId;
import com.like.pro5.util.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ThreadLocalLogTrace implements LogTrace {

    private static final String START_PREFIX = "==>";
    private static final String COMPLETE_PREFIX = "<==";
    private static final String EX_PREFIX = "<X==";

    /* traceId 를 보관, 다른 곳에 넘겨주기 위해 */
    private ThreadLocal<TraceId> traceIdHolder = new ThreadLocal<>();

    @Override
    /**
     * 로그 시작, 메세지 출력을 위해 메세지를 파라미터로 받는다.
     */
    public TraceStatus begin(String message) {
        syncTraceId();
        TraceId traceId = traceIdHolder.get();

        // 시작 시간
        Long startTimeMs = System.currentTimeMillis();

        // 로그 출력
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
        return new TraceStatus(traceId, startTimeMs, message);
    }

    /**
     * 로그 종료, 진행 중인 로그 상태를 파라미터로 받는다.
     */
    @Override
    public void end(TraceStatus status) {
        complete(status, null);
    }


    /**
     * 로그 진행 중 예외가 발생했을 경우 사용
     */
    @Override
    public void exception(TraceStatus status, Exception e) {
        complete(status, e);
    }

    // == 비공개 메서드 ==
    private void complete(TraceStatus status, Exception e) {
        // 종료 시간
        Long stopTimeMs = System.currentTimeMillis();

        // 종료 시간 - 시작 시간을 통해 총 소요 시간을 구한다.
        long resultTimeMs = stopTimeMs - status.getStartTimeMs();

        // log 출력을 위해 traceId 추출
        TraceId traceId = status.getTraceId();

        // log 출력
        if (e == null) {
            log.info("[{}] {}{} time={}ms", traceId.getId(),
                    addSpace(COMPLETE_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs);
        } else {
            log.info("[{}] {}{} time={}ms ex={}", traceId.getId(),
                    addSpace(EX_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs, e.toString());
        }

        releaseTraceId();
    }

    /**
     * level = 0
     * level = 1 : |==>
     * level = 2 : |   |==>
     *
     * level = 2, ex : ex  |   |<X==
     * level = 1, ex : ex  |<X==
     */
    private String addSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append(
                    (i == level - 1) ? "|" + prefix : "|   "
            );
        }
        return sb.toString();
    }

    /**
     * traceId 를 새로 만들거나 앞선 로그의 traceId 를 참고해서 동기화
     */
    private void syncTraceId() {
        TraceId traceId = traceIdHolder.get();
        if (traceId == null) {
            traceIdHolder.set(new TraceId());
        } else {
            traceIdHolder.set(traceId.createNextId());
        }
    }

    /**
     * traceIdHolder destroy
     * traceIdHolder previousId
     */
    private void releaseTraceId() {
        TraceId traceId = traceIdHolder.get();
        // 만약 traceIdHolder 가 첫 번째 level 이라면 == 로그가 끝까지 갔다 왔다면 == 마지막에 도달했다.
        if (traceId.isFirstLevel()) {
            traceIdHolder.remove();
        } else {
            // 로그가 찍히던 중간이라면, 이전 단계 로그 아이디
            traceIdHolder.set(traceId.createPreviousId());
        }
    }

}

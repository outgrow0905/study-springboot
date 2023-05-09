package com.springboot.advanced.ch1.v5;

import com.springboot.advanced.ch1.trace.LogTrace;
import com.springboot.advanced.ch1.trace.TraceId;
import com.springboot.advanced.ch1.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyLogTraceV5 implements LogTrace {

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";
    private final ThreadLocal<TraceId> traceIdHolder = new ThreadLocal<>();

    @Override
    public TraceStatus begin(String message) {
        TraceId traceId = getTraceId();
        long startTime = System.currentTimeMillis();
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
        return new TraceStatus(traceId, startTime, message);
    }

    @Override
    public void end(TraceStatus status) {
        complete(status, null);
    }

    @Override
    public void exception(TraceStatus status, Exception e) {
        complete(status, e);
    }

    private TraceId getTraceId() {
        if (null == traceIdHolder.get()) {
            traceIdHolder.set(new TraceId());
            return traceIdHolder.get();
        }

        traceIdHolder.set(traceIdHolder.get().createNextTraceId());
        return traceIdHolder.get();
    }

    private void complete(TraceStatus status, Exception e) {
        Long endTime = System.currentTimeMillis();
        Long boxUsage = endTime - status.getStartTime();
        TraceId traceId = status.getTraceId();
        if (e == null) {
            log.info("[{}] {}{} time={}ms", traceId.getId(), addSpace(COMPLETE_PREFIX, traceId.getLevel()), status.getMessage(), boxUsage);
        } else {
            log.error("[{}] {}{} time={}ms ex={}", traceId.getId(), addSpace(EX_PREFIX, traceId.getLevel()), status.getMessage(), boxUsage, e.getClass().getName());
        }

        releaseTraceIdHolder();
    }

    private void releaseTraceIdHolder() {
        if (traceIdHolder.get().isFirstLevel()) {
            traceIdHolder.remove();
        } else {
            traceIdHolder.set(traceIdHolder.get().createPreviousTraceId());
        }
    }

    private Object addSpace(String prefix, int level) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < level; i++) {
            stringBuilder.append((i == level - 1) ? "|" + prefix : "|     ");
        }
        return stringBuilder.toString();
    }
}

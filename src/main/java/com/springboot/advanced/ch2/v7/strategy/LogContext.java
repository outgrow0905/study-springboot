package com.springboot.advanced.ch2.v7.strategy;

import com.springboot.advanced.ch1.trace.LogTrace;
import com.springboot.advanced.ch1.trace.TraceStatus;


public class LogContext {
    private final LogTrace logTrace;

    public LogContext(LogTrace logTrace) {
        this.logTrace = logTrace;
    }

    public <T> T execute(String message, LogContextStrategy<T> strategy) {
        TraceStatus status = null;
        try {
            status = logTrace.begin(message);
            T t = strategy.action();
            logTrace.end(status);
            return t;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}

package com.springboot.advanced.ch2.v7.strategy;

import com.springboot.advanced.ch1.trace.LogTrace;
import com.springboot.advanced.ch1.trace.TraceStatus;


public class LogContext {
    private final LogTrace logTraceV5;

    public LogContext(LogTrace logTraceV5) {
        this.logTraceV5 = logTraceV5;
    }

    public <T> T execute(String message, LogContextStrategy<T> strategy) {
        TraceStatus status = null;
        try {
            status = logTraceV5.begin(message);
            T t = strategy.action();
            logTraceV5.end(status);
            return t;
        } catch (Exception e) {
            logTraceV5.exception(status, e);
            throw e;
        }
    }
}

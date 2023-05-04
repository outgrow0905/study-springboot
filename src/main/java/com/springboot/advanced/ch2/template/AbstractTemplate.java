package com.springboot.advanced.ch2.template;

import com.springboot.advanced.ch1.trace.LogTrace;
import com.springboot.advanced.ch1.trace.TraceStatus;

public abstract class AbstractTemplate<T> {

    private final LogTrace logTraceV5;

    public AbstractTemplate(LogTrace logTraceV5) {
        this.logTraceV5 = logTraceV5;
    }

    public T execute(String message) {
        TraceStatus status = null;
        try {
            status = logTraceV5.begin(message);
            T t = action();
            logTraceV5.end(status);
            return t;
        } catch (Exception e) {
            logTraceV5.exception(status, e);
            throw e;
        }
    }

    protected abstract T action();
}

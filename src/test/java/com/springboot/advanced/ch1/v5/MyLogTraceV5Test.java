package com.springboot.advanced.ch1.v5;

import static org.junit.jupiter.api.Assertions.*;

import com.springboot.advanced.ch1.trace.LogTrace;
import com.springboot.advanced.ch1.trace.TraceStatus;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class MyLogTraceV5Test {
    LogTrace logTrace = new MyLogTraceV5();

    @Test
    void beginAndEnd() throws InterruptedException {
        CompletableFuture.runAsync(() -> {
            TraceStatus step1 = logTrace.begin("t1 hello");
            TraceStatus step2 = logTrace.begin("t1 world");
            logTrace.end(step2);
            logTrace.end(step1);
        });
        CompletableFuture.runAsync(() -> {
            TraceStatus step1 = logTrace.begin("t2 hello");
            TraceStatus step2 = logTrace.begin("t2 world");
            logTrace.end(step2);
            logTrace.end(step1);
        });

        Thread.sleep(3000);
    }

    @Test
    void beginAndException() throws InterruptedException {
        CompletableFuture.runAsync(() -> {
            TraceStatus step1 = logTrace.begin("t1 hello");
            TraceStatus step2 = logTrace.begin("t1 world");
            logTrace.exception(step2, new IllegalStateException());
            logTrace.exception(step1, new IllegalStateException());
        });
        CompletableFuture.runAsync(() -> {
            TraceStatus step1 = logTrace.begin("t2 hello");
            TraceStatus step2 = logTrace.begin("t2 world");
            logTrace.exception(step2, new IllegalStateException());
            logTrace.exception(step1, new IllegalStateException());
        });

        Thread.sleep(3000);
    }
}
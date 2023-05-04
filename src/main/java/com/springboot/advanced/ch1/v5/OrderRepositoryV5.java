package com.springboot.advanced.ch1.v5;

import com.springboot.advanced.ch1.trace.LogTrace;
import com.springboot.advanced.ch1.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV5 {

    private final LogTrace logTraceV5;
    public void save(String itemId) {

        TraceStatus status = null;
        try {
            status = logTraceV5.begin("OrderRepository save()");
            if (itemId.equals("ex")) {
                throw new IllegalStateException("IllegalStateException occur");
            }
            sleep();
            logTraceV5.end(status);
        } catch (Exception e) {
            logTraceV5.exception(status, e);
            throw e;
        }
    }

    private static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

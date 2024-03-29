package com.springboot.advanced.ch1.v4;

import com.springboot.advanced.ch1.trace.LogTrace;
import com.springboot.advanced.ch1.trace.TraceStatus;
import com.springboot.advanced.ch1.v2.MyTracerV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {

    private final LogTrace logTraceV4;
    public void save(String itemId) {

        TraceStatus status = null;
        try {
            status = logTraceV4.begin("OrderRepository save()");
            if (itemId.equals("ex")) {
                throw new IllegalStateException("IllegalStateException occur");
            }
            sleep();
            logTraceV4.end(status);
        } catch (Exception e) {
            logTraceV4.exception(status, e);
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

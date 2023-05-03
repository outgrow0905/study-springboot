package com.springboot.advanced.ch1.v3;

import com.springboot.advanced.ch1.trace.TraceId;
import com.springboot.advanced.ch1.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class OrderRepositoryV3 {

    private final MyTracerV3 tracer;
    public void save(TraceId traceId, String itemId) {
        TraceStatus status = null;
        try {
            status = tracer.begin(traceId, "OrderRepository save()");
            if (itemId.equals("ex")) {
                throw new IllegalStateException("IllegalStateException occur");
            }
            sleep();
            tracer.end(status);
        } catch (Exception e) {
            tracer.exception(status, e);
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

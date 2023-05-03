package com.springboot.advanced.ch1.v2;

import com.springboot.advanced.ch1.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {

    private final MyTracerV2 tracer;
    public void save(String itemId) {

        TraceStatus status = null;
        try {
            status = tracer.begin("OrderRepository save()");
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

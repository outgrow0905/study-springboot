package com.springboot.advanced.ch1.v1;

import com.springboot.advanced.ch1.trace.TraceStatus;
import com.springboot.advanced.ch1.v2.MyTracerV2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MyTracerTest {
    @Autowired
    private MyTracerV2 tracer;

    @Test
    void beginAndEnd() {
        TraceStatus status = tracer.begin("hello");
        tracer.end(status);
    }

    @Test
    void beginAndException() {
        TraceStatus status = tracer.begin("hello");
        tracer.exception(status, new IllegalStateException());
    }
}
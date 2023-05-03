package com.springboot.advanced.ch1.v3;

import com.springboot.advanced.ch1.trace.TraceStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MyTracerV3Test {
    @Autowired
    private MyTracerV3 tracer;

    @Test
    void beginAndEnd() {
        TraceStatus hello = tracer.begin("hello");
        TraceStatus world = tracer.begin(hello.getTraceId(), "world");
        tracer.end(world);
        tracer.end(hello);
    }

    @Test
    void beginAndException() {
        TraceStatus hello = tracer.begin("hello");
        TraceStatus world = tracer.begin(hello.getTraceId(), "world");
        tracer.exception(world, new IllegalStateException());
        tracer.exception(hello, new IllegalStateException());
    }
}
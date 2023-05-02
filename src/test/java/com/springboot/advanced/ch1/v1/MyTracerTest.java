package com.springboot.advanced.ch1.v1;

import com.springboot.advanced.ch1.trace.TraceStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MyTracerTest {
    @Autowired
    private MyTracer tracer;

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
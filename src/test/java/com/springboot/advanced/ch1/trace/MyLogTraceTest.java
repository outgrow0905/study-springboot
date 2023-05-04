package com.springboot.advanced.ch1.trace;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MyLogTraceTest {
    @Test
    void beginAndEnd() {
        MyLogTrace trace = new MyLogTrace();
        TraceStatus step1 = trace.begin("step1");
        TraceStatus step2 = trace.begin("step2");
        trace.end(step2);
        trace.end(step1);
    }

    @Test
    void beginAndException() {
        MyLogTrace trace = new MyLogTrace();
        TraceStatus hello = trace.begin("hello");
        TraceStatus world = trace.begin("world");
        trace.exception(world, new IllegalStateException());
        trace.exception(hello, new IllegalStateException());
    }
}
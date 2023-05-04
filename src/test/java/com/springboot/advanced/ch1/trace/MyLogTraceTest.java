package com.springboot.advanced.ch1.trace;

import com.springboot.advanced.ch1.v4.MyLogTraceV4;
import org.junit.jupiter.api.Test;

class MyLogTraceTest {
    @Test
    void beginAndEnd() {
        MyLogTraceV4 trace = new MyLogTraceV4();
        TraceStatus step1 = trace.begin("step1");
        TraceStatus step2 = trace.begin("step2");
        trace.end(step2);
        trace.end(step1);
    }

    @Test
    void beginAndException() {
        MyLogTraceV4 trace = new MyLogTraceV4();
        TraceStatus hello = trace.begin("hello");
        TraceStatus world = trace.begin("world");
        trace.exception(world, new IllegalStateException());
        trace.exception(hello, new IllegalStateException());
    }
}
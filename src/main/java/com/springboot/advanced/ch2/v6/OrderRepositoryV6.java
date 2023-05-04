package com.springboot.advanced.ch2.v6;

import com.springboot.advanced.ch1.trace.LogTrace;
<<<<<<< HEAD
import com.springboot.advanced.ch2.v6.template.AbstractTemplate;
=======
import com.springboot.advanced.ch1.trace.TraceStatus;
import com.springboot.advanced.ch2.template.AbstractTemplate;
>>>>>>> 438818ac4bcd8ad08140edf8a4d7967c357e60f7
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV6 {

    private final LogTrace logTraceV5;
    public void save(String itemId) {
        new AbstractTemplate<>(logTraceV5) {
            @Override
            protected Object action() {
                if (itemId.equals("ex")) {
                 throw new IllegalStateException("IllegalStateException occur");
                }
                sleep();
                return null;
            }
        }.execute("OrderRepository save()");
    }

    private static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

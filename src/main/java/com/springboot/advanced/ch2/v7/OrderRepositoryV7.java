package com.springboot.advanced.ch2.v7;

import com.springboot.advanced.ch1.trace.LogTrace;
import com.springboot.advanced.ch2.v6.template.AbstractTemplate;
import com.springboot.advanced.ch2.v7.strategy.LogContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV7 {

    private final LogContext context;
    public void save(String itemId) {
        context.execute(
            "OrderRepository save()",
            () -> {
                if (itemId.equals("ex")) {
                    throw new IllegalStateException("IllegalStateException occur");
                }
                sleep();
                return null;
            }
        );
    }

    private static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

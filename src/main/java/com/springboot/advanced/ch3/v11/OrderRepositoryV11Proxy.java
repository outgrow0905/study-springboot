package com.springboot.advanced.ch3.v11;

import com.springboot.advanced.ch1.trace.LogTrace;
import com.springboot.advanced.ch2.v7.strategy.LogContext;

public class OrderRepositoryV11Proxy implements OrderRepositoryV11 {

    private final OrderRepositoryV11 orderRepository;
    private final LogContext context;

    public OrderRepositoryV11Proxy(OrderRepositoryV11 orderRepository, LogTrace logTrace) {
        this.orderRepository = orderRepository;
        this.context = new LogContext(logTrace);
    }

    @Override
    public void save(String itemId) {
        context.execute(
            "OrderRepository save()",
            () -> {
                orderRepository.save(itemId);
                return null;
                }
            );
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

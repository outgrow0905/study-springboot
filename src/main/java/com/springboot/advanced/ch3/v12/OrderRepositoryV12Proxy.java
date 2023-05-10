package com.springboot.advanced.ch3.v12;

import com.springboot.advanced.ch1.trace.LogTrace;
import com.springboot.advanced.ch2.v7.strategy.LogContext;

public class OrderRepositoryV12Proxy extends OrderRepositoryV12{

    private final OrderRepositoryV12 orderRepository;
    private final LogContext context;

    public OrderRepositoryV12Proxy(OrderRepositoryV12 orderRepository, LogTrace logTrace) {
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
}

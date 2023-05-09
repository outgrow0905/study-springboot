package com.springboot.advanced.ch3.v11;

import com.springboot.advanced.ch1.trace.LogTrace;
import com.springboot.advanced.ch2.v7.strategy.LogContext;


public class OrderServiceV11Proxy implements OrderServiceV11 {

    private final OrderServiceV11 orderService;
    private final LogContext context;

    public OrderServiceV11Proxy(OrderServiceV11 orderService, LogTrace logTrace) {
        this.orderService = orderService;
        this.context = new LogContext(logTrace);
    }

    @Override
    public void orderItem(String itemId) {
        context.execute(
            "OrderService orderItem()",
            () -> {
                orderService.orderItem(itemId);
                return null;
            });
    }
}

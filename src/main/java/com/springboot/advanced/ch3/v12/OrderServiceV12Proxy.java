package com.springboot.advanced.ch3.v12;

import com.springboot.advanced.ch1.trace.LogTrace;
import com.springboot.advanced.ch2.v7.strategy.LogContext;

public class OrderServiceV12Proxy extends OrderServiceV12{

    private final OrderServiceV12 orderService;
    private final LogContext context;

    public OrderServiceV12Proxy(OrderServiceV12 orderService,
        LogTrace logTrace) {
        super(null);
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

package com.springboot.advanced.ch3.v11;

import com.springboot.advanced.ch1.trace.LogTrace;
import com.springboot.advanced.ch2.v7.strategy.LogContext;
import lombok.RequiredArgsConstructor;

public class OrderControllerV11Proxy implements OrderControllerV11{

    private final OrderControllerV11 orderController;
    private final LogContext context;

    public OrderControllerV11Proxy(OrderControllerV11 orderController, LogTrace logTrace) {
        this.orderController = orderController;
        this.context = new LogContext(logTrace);
    }

    @Override
    public String request(String itemId) {
        return context.execute(
            "OrderController request()",
            () -> orderController.request(itemId));
    }

    @Override
    public String noLog(String itemId) {
        return orderController.request(itemId);
    }
}

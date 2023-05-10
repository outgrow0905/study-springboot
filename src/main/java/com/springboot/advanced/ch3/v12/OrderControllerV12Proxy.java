package com.springboot.advanced.ch3.v12;

import com.springboot.advanced.ch1.trace.LogTrace;
import com.springboot.advanced.ch2.v7.strategy.LogContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class OrderControllerV12Proxy extends OrderControllerV12 {
    private final OrderControllerV12 orderController;
    private final LogContext context;

    public OrderControllerV12Proxy(OrderControllerV12 orderController, LogTrace logTrace) {
        super(null);
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
        return orderController.noLog(itemId);
    }
}

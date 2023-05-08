package com.springboot.advanced.ch3.v8;

public class OrderControllerV8Impl implements OrderControllerV8 {
    private final OrderServiceV8 orderService;

    public OrderControllerV8Impl(OrderServiceV8 orderService) {
        this.orderService = orderService;
    }

    @Override
    public String request(String itemId) {
        orderService.orderItem(itemId);
        return "ok";
    }

    @Override
    public String noLog() {
        return "ok";
    }
}

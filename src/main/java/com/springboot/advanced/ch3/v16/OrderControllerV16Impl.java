package com.springboot.advanced.ch3.v16;

public class OrderControllerV16Impl implements OrderControllerV16 {
    private final OrderServiceV16 orderService;

    public OrderControllerV16Impl(OrderServiceV16 orderService) {
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

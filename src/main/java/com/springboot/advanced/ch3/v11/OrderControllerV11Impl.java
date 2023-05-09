package com.springboot.advanced.ch3.v11;

public class OrderControllerV11Impl implements OrderControllerV11 {
    private final OrderServiceV11 orderService;

    public OrderControllerV11Impl(OrderServiceV11 orderService) {
        this.orderService = orderService;
    }

    @Override
    public String request(String itemId) {
        orderService.orderItem(itemId);
        return "ok";
    }

    @Override
    public String noLog(String itemId) {
        orderService.orderItem(itemId);
        return "ok";
    }
}

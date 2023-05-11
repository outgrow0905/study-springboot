package com.springboot.advanced.ch3.v14;


public class OrderControllerV14Impl implements OrderControllerV14 {
    private final OrderServiceV14 orderService;

    public OrderControllerV14Impl(OrderServiceV14 orderService) {
        this.orderService = orderService;
    }
    public String request(String itemId) {
        orderService.orderItem(itemId);
        return "ok";
    }

    public String noLog(String itemId) {
        return "ok";
    }
}

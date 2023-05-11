package com.springboot.advanced.ch3.v13;


public class OrderControllerV13Impl implements OrderControllerV13{
    private final OrderServiceV13 orderService;

    public OrderControllerV13Impl(OrderServiceV13 orderService) {
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

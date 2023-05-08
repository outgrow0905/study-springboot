package com.springboot.advanced.ch3.v9;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping
@ResponseBody
public class OrderControllerV9 {
    private final OrderServiceV9 orderService;

    public OrderControllerV9(OrderServiceV9 orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/v9/request")
    public String request(String itemId) {
        orderService.orderItem(itemId);
        return "ok";
    }

    @GetMapping("/v9/no-log")
    public String noLog() {
        return "ok";
    }
}

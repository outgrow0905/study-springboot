package com.springboot.advanced.ch3.v17;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class OrderControllerV17 {
    private final OrderServiceV17 orderService;

    public OrderControllerV17(OrderServiceV17 orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/v17/request")
    public String request(String itemId) {
        orderService.orderItem(itemId);
        return "ok";
    }

    @GetMapping("/v17/no-log")
    public String noLog() {
        return "ok";
    }
}

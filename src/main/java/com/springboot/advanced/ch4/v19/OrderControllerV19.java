package com.springboot.advanced.ch4.v19;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class OrderControllerV19 {
    private final OrderServiceV19 orderService;

    public OrderControllerV19(OrderServiceV19 orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/v19/request")
    public String request(String itemId) {
        orderService.orderItem(itemId);
        return "ok";
    }

    @GetMapping("/v19/no-log")
    public String noLog() {
        return "ok";
    }
}

package com.springboot.advanced.ch4.v18;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class OrderControllerV18 {
    private final OrderServiceV18 orderService;

    public OrderControllerV18(OrderServiceV18 orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/v18/request")
    public String request(String itemId) {
        orderService.orderItem(itemId);
        return "ok";
    }

    @GetMapping("/v18/no-log")
    public String noLog() {
        return "ok";
    }
}

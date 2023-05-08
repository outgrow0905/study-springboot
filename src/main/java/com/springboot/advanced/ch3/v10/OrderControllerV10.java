package com.springboot.advanced.ch3.v10;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class OrderControllerV10 {
    private final OrderServiceV10 orderServiceV10;

    public OrderControllerV10(OrderServiceV10 orderServiceV10) {
        this.orderServiceV10 = orderServiceV10;
    }

    @GetMapping("/v10/request")
    public String request(String itemId) {
        orderServiceV10.orderItem(itemId);
        return "ok";
    }

    @GetMapping("/v10/no-log")
    public String noLog() {
        return "ok";
    }
}

package com.springboot.advanced.ch1.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/v1/request")
    public String request(String itemId) {
        orderService.orderItem(itemId);
        return "ok";
    }
}

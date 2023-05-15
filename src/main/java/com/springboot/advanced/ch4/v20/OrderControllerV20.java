package com.springboot.advanced.ch4.v20;

import com.springboot.advanced.ch4.v19.OrderServiceV19;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class OrderControllerV20 {
    private final OrderServiceV20 orderService;

    public OrderControllerV20(OrderServiceV20 orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/v20/request")
    public String request(String itemId) {
        orderService.orderItem(itemId);
        return "ok";
    }

    @GetMapping("/v20/no-log")
    public String noLog() {
        return "ok";
    }
}

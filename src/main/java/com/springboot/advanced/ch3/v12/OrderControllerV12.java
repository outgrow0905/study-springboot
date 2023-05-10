package com.springboot.advanced.ch3.v12;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping
@ResponseBody
public class OrderControllerV12 {
    private final OrderServiceV12 orderService;

    public OrderControllerV12(OrderServiceV12 orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/v12/request")
    public String request(String itemId) {
        orderService.orderItem(itemId);
        return "ok";
    }

    @GetMapping("/v12/no-log")
    public String noLog(String itemId) {
        return "ok";
    }
}

package com.springboot.advanced.ch3.v15;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping
@RequiredArgsConstructor
@ResponseBody
public class OrderControllerV15 {
    private final OrderServiceV15 orderService;

    @GetMapping("/v15/request")
    public String request(String itemId) {
        orderService.orderItem(itemId);
        return "ok";
    }

    @GetMapping("/v15/no-log")
    public String noLog() {
        return "ok";
    }
}

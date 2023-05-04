package com.springboot.advanced.ch2.v7;

import com.springboot.advanced.ch1.trace.LogTrace;
import com.springboot.advanced.ch2.v6.template.AbstractTemplate;
import com.springboot.advanced.ch2.v7.strategy.LogContext;
import com.springboot.advanced.ch2.v7.strategy.LogContextStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV7 {
    private final OrderServiceV7 orderService;
    private final LogContext context;

    @GetMapping("/v7/request")
    public String request(String itemId) {
        return context.execute(
            "OrderController request()",
            () -> {
                orderService.orderItem(itemId);
                return "ok";
        });
    }
}

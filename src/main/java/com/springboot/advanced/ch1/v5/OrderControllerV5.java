package com.springboot.advanced.ch1.v5;

import com.springboot.advanced.ch1.trace.LogTrace;
import com.springboot.advanced.ch1.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV5 {
    private final OrderServiceV5 orderService;
    private final LogTrace logTraceV5;

    @GetMapping("/v5/request")
    public String request(String itemId) {
        TraceStatus status = null;
        try {
            status = logTraceV5.begin("OrderController request()");
            orderService.orderItem(itemId);
            logTraceV5.end(status);
            return "ok";
        } catch (Exception e) {
            logTraceV5.exception(status, e);
            throw e;
        }
    }
}

package com.springboot.advanced.ch1.v4;

import com.springboot.advanced.ch1.trace.LogTrace;
import com.springboot.advanced.ch1.trace.TraceStatus;
import com.springboot.advanced.ch1.v2.MyTracerV2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV4 {
    private final OrderServiceV4 orderService;
    private final LogTrace tracer;

    @GetMapping("/v4/request")
    public String request(String itemId) {
        TraceStatus status = null;
        try {
            status = tracer.begin("OrderController request()");
            orderService.orderItem(itemId);
            tracer.end(status);
            return "ok";
        } catch (Exception e) {
            tracer.exception(status, e);
            throw e;
        }
    }
}

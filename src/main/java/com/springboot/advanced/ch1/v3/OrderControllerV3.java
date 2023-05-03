package com.springboot.advanced.ch1.v3;

import com.springboot.advanced.ch1.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV3 {
    private final OrderServiceV3 orderService;
    private final MyTracerV3 tracer;

    @GetMapping("/v3/request")
    public String request(String itemId) {
        TraceStatus status = null;
        try {
            status = tracer.begin("OrderController request()");
            orderService.orderItem(status.getTraceId(), itemId);
            tracer.end(status);
            return "ok";
        } catch (Exception e) {
            tracer.exception(status, e);
            throw e;
        }
    }
}

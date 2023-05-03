package com.springboot.advanced.ch1.v2;

import com.springboot.advanced.ch1.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {
    private final OrderServiceV2 orderService;
    private final MyTracerV2 tracer;

    @GetMapping("/v2/request")
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

package com.springboot.advanced.ch2.v6;

import com.springboot.advanced.ch1.trace.LogTrace;
import com.springboot.advanced.ch2.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV6 {
    private final OrderServiceV6 orderService;
    private final LogTrace logTraceV5;
//    private AbstractLogTrace logTrace;

    @GetMapping("/v6/request")
    public String request(String itemId) {
        AbstractTemplate<String> template = new AbstractTemplate<>(logTraceV5) {
            @Override
            protected String action() {
                orderService.orderItem(itemId);
                return "ok";
            }
        };
        return template.execute("OrderController request()");
    }
}

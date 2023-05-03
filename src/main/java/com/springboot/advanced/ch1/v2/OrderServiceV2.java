package com.springboot.advanced.ch1.v2;

import com.springboot.advanced.ch1.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {
    private final OrderRepositoryV2 orderRepository;
    private final MyTracerV2 tracer;

    public void orderItem(String itemId) {
        TraceStatus status = null;
        try {
            status = tracer.begin("OrderService orderItem()");
            orderRepository.save(itemId);
            tracer.end(status);
        } catch (Exception e) {
            tracer.exception(status, e);
            throw e;
        }

    }
}

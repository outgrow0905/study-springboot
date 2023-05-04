package com.springboot.advanced.ch1.v4;

import com.springboot.advanced.ch1.trace.LogTrace;
import com.springboot.advanced.ch1.trace.TraceStatus;
import com.springboot.advanced.ch1.v2.MyTracerV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV4 {
    private final OrderRepositoryV4 orderRepository;
    private final LogTrace tracer;

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

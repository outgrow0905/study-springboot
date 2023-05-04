package com.springboot.advanced.ch1.v5;

import com.springboot.advanced.ch1.trace.LogTrace;
import com.springboot.advanced.ch1.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV5 {
    private final OrderRepositoryV5 orderRepository;
    private final LogTrace logTraceV5;

    public void orderItem(String itemId) {
        TraceStatus status = null;
        try {
            status = logTraceV5.begin("OrderService orderItem()");
            orderRepository.save(itemId);
            logTraceV5.end(status);
        } catch (Exception e) {
            logTraceV5.exception(status, e);
            throw e;
        }

    }
}

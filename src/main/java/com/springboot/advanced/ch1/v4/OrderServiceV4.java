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
    private final LogTrace logTraceV4;

    public void orderItem(String itemId) {
        TraceStatus status = null;
        try {
            status = logTraceV4.begin("OrderService orderItem()");
            orderRepository.save(itemId);
            logTraceV4.end(status);
        } catch (Exception e) {
            logTraceV4.exception(status, e);
            throw e;
        }

    }
}

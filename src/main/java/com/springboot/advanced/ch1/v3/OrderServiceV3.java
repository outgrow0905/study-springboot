package com.springboot.advanced.ch1.v3;

import com.springboot.advanced.ch1.trace.TraceId;
import com.springboot.advanced.ch1.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceV3 {
    private final OrderRepositoryV3 orderRepository;
    private final MyTracerV3 tracer;

    public void orderItem(TraceId traceId, String itemId) {
        TraceStatus status = null;
        try {
            status = tracer.begin(traceId, "OrderService orderItem()");
            orderRepository.save(status.getTraceId(), itemId);
            tracer.end(status);
        } catch (Exception e) {
            tracer.exception(status, e);
            throw e;
        }

    }
}

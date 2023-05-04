package com.springboot.advanced.ch2.v7;

import com.springboot.advanced.ch1.trace.LogTrace;
import com.springboot.advanced.ch2.v6.template.AbstractTemplate;
import com.springboot.advanced.ch2.v7.strategy.LogContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV7 {
    private final OrderRepositoryV7 orderRepository;

    private final LogContext context;

    public void orderItem(String itemId) {
        context.execute(
            "OrderService orderItem()",
            () -> {
                orderRepository.save(itemId);
                return null;
            });
    }
}

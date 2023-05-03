package com.springboot.advanced.ch1.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}

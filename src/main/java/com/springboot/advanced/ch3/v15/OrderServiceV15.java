package com.springboot.advanced.ch3.v15;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderServiceV15 {
    private final OrderRepositoryV15 orderRepository;

    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}

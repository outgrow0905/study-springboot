package com.springboot.advanced.ch3.v17;

import org.springframework.stereotype.Service;

@Service
public class OrderServiceV17 {

    private final OrderRepositoryV17 orderRepository;

    public OrderServiceV17(OrderRepositoryV17 orderRepository) {
        this.orderRepository = orderRepository;
    }


    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}

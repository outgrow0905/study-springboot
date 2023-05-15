package com.springboot.advanced.ch4.v20;

import org.springframework.stereotype.Service;

@Service
public class OrderServiceV20 {

    private final OrderRepositoryV20 orderRepository;

    public OrderServiceV20(OrderRepositoryV20 orderRepository) {
        this.orderRepository = orderRepository;
    }


    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}

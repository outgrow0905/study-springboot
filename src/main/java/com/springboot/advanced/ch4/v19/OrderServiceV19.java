package com.springboot.advanced.ch4.v19;

import org.springframework.stereotype.Service;

@Service
public class OrderServiceV19 {

    private final OrderRepositoryV19 orderRepository;

    public OrderServiceV19(OrderRepositoryV19 orderRepository) {
        this.orderRepository = orderRepository;
    }


    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}

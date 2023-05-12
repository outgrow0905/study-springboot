package com.springboot.advanced.ch4.v18;

import org.springframework.stereotype.Service;

@Service
public class OrderServiceV18 {

    private final OrderRepositoryV18 orderRepository;

    public OrderServiceV18(OrderRepositoryV18 orderRepository) {
        this.orderRepository = orderRepository;
    }


    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}

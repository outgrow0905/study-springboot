package com.springboot.advanced.ch3.v10;

import org.springframework.stereotype.Service;

@Service
public class OrderServiceV10 {

    private final OrderRepositoryV10 orderRepositoryV10;

    public OrderServiceV10(OrderRepositoryV10 orderRepositoryV10) {
        this.orderRepositoryV10 = orderRepositoryV10;
    }


    public void orderItem(String itemId) {
        orderRepositoryV10.save(itemId);
    }
}

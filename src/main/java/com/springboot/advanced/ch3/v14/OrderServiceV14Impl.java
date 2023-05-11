package com.springboot.advanced.ch3.v14;

public class OrderServiceV14Impl implements OrderServiceV14 {

    private final OrderRepositoryV14 orderRepository;

    public OrderServiceV14Impl(OrderRepositoryV14 orderRepository) {
        this.orderRepository = orderRepository;
    }


    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}

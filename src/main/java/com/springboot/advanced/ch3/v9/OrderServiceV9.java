package com.springboot.advanced.ch3.v9;

public class OrderServiceV9 {

    private final OrderRepositoryV9 orderRepository;

    public OrderServiceV9(OrderRepositoryV9 orderRepository) {
        this.orderRepository = orderRepository;
    }


    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}

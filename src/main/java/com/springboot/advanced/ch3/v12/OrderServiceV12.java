package com.springboot.advanced.ch3.v12;

public class OrderServiceV12 {

    private final OrderRepositoryV12 orderRepository;

    public OrderServiceV12(OrderRepositoryV12 orderRepository) {
        this.orderRepository = orderRepository;
    }


    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}

package com.springboot.advanced.ch3.v13;

public class OrderServiceV13Impl implements OrderServiceV13{

    private final OrderRepositoryV13 orderRepository;

    public OrderServiceV13Impl(OrderRepositoryV13 orderRepository) {
        this.orderRepository = orderRepository;
    }


    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}

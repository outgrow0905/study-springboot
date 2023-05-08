package com.springboot.advanced.ch3.v8;

public class OrderServiceV8Impl implements OrderServiceV8 {

    private final OrderRepositoryV8 orderRepository;

    public OrderServiceV8Impl(OrderRepositoryV8 orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}

package com.springboot.advanced.ch3.v16;

public class OrderServiceV16Impl implements OrderServiceV16 {

    private final OrderRepositoryV16 orderRepository;

    public OrderServiceV16Impl(OrderRepositoryV16 orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}

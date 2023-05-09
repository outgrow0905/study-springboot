package com.springboot.advanced.ch3.v11;

public class OrderServiceV11Impl implements OrderServiceV11 {

    private final OrderRepositoryV11 orderRepository;

    public OrderServiceV11Impl(OrderRepositoryV11 orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}

package com.springboot.advanced.ch3.v9;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProxyV9Config {
    @Bean
    public OrderControllerV9 orderControllerV9() {
        return new OrderControllerV9(orderServiceV9());
    }

    @Bean
    public OrderServiceV9 orderServiceV9() {
        return new OrderServiceV9(orderRepositoryV9());
    }

    @Bean
    public OrderRepositoryV9 orderRepositoryV9() {
        return new OrderRepositoryV9();
    }
}

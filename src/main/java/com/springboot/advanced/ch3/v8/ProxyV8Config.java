package com.springboot.advanced.ch3.v8;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProxyV8Config {
    @Bean
    public OrderControllerV8 orderControllerV8() {
        return new OrderControllerV8Impl(orderServiceV8());
    }

    @Bean
    public OrderServiceV8 orderServiceV8() {
        return new OrderServiceV8Impl(orderRepositoryV8());
    }

    @Bean
    public OrderRepositoryV8 orderRepositoryV8() {
        return new OrderRepositoryV8Impl();
    }
}

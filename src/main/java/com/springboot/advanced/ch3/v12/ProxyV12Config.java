package com.springboot.advanced.ch3.v12;

import com.springboot.advanced.ch1.trace.LogTrace;
import com.springboot.advanced.ch3.v11.OrderControllerV11;
import com.springboot.advanced.ch3.v11.OrderControllerV11Impl;
import com.springboot.advanced.ch3.v11.OrderControllerV11Proxy;
import com.springboot.advanced.ch3.v11.OrderRepositoryV11;
import com.springboot.advanced.ch3.v11.OrderRepositoryV11Impl;
import com.springboot.advanced.ch3.v11.OrderRepositoryV11Proxy;
import com.springboot.advanced.ch3.v11.OrderServiceV11;
import com.springboot.advanced.ch3.v11.OrderServiceV11Impl;
import com.springboot.advanced.ch3.v11.OrderServiceV11Proxy;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ProxyV12Config {

    @Bean
    public OrderControllerV12 orderControllerV12(LogTrace logTraceV5) {
        return new OrderControllerV12Proxy(
            new OrderControllerV12(orderServiceV12(logTraceV5)),
            logTraceV5
        );
    }

    @Bean
    public OrderServiceV12 orderServiceV12(LogTrace logTraceV5) {
        return new OrderServiceV12Proxy(
            new OrderServiceV12(orderRepositoryV12(logTraceV5)),
            logTraceV5
        );
    }

    @Bean
    public OrderRepositoryV12 orderRepositoryV12(LogTrace logTraceV5) {
        return new OrderRepositoryV12Proxy(new OrderRepositoryV12(), logTraceV5);
    }
}

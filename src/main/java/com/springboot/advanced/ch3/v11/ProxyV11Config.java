package com.springboot.advanced.ch3.v11;

import com.springboot.advanced.ch1.trace.LogTrace;
import com.springboot.advanced.ch1.v5.MyLogTraceV5;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ProxyV11Config {

    @Bean
    public OrderControllerV11 orderControllerV11(LogTrace logTraceV5) {
        return new OrderControllerV11Proxy(
            new OrderControllerV11Impl(orderServiceV11(logTraceV5)), logTraceV5
        );
    }

    @Bean
    public OrderServiceV11 orderServiceV11(LogTrace logTraceV5) {
        return new OrderServiceV11Proxy(
            new OrderServiceV11Impl(orderRepositoryV11(logTraceV5)), logTraceV5
        );
    }

    @Bean
    public OrderRepositoryV11 orderRepositoryV11(LogTrace logTraceV5) {
        return new OrderRepositoryV11Proxy(new OrderRepositoryV11Impl(), logTraceV5
        );
    }
}

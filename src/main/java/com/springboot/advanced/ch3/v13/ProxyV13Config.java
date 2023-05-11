package com.springboot.advanced.ch3.v13;

import com.springboot.advanced.ch1.trace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

@Configuration
@RequiredArgsConstructor
public class ProxyV13Config {
    @Bean
    public OrderControllerV13 orderControllerV13(LogTrace logTraceV5) {
        return (OrderControllerV13)Proxy.newProxyInstance(
                OrderControllerV13.class.getClassLoader(),
                new Class[]{OrderControllerV13.class},
                new LogInvocationHandler(new OrderControllerV13Impl(orderServiceV13(logTraceV5)), logTraceV5)
        );
    }

    @Bean
    public OrderServiceV13 orderServiceV13(LogTrace logTraceV5) {
        return (OrderServiceV13)Proxy.newProxyInstance(
                OrderServiceV13.class.getClassLoader(),
                new Class[]{OrderServiceV13.class},
                new LogInvocationHandler(new OrderServiceV13Impl(orderRepositoryV13(logTraceV5)), logTraceV5)
        );
    }

    @Bean
    public OrderRepositoryV13 orderRepositoryV13(LogTrace logTraceV5) {
        return (OrderRepositoryV13)Proxy.newProxyInstance(
                OrderRepositoryV13.class.getClassLoader(),
                new Class[]{OrderRepositoryV13.class},
                new LogInvocationHandler(new OrderRepositoryV13Impl(), logTraceV5)
        );
    }
}

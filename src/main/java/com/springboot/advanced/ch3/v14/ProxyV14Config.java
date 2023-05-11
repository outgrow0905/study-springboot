package com.springboot.advanced.ch3.v14;

import com.springboot.advanced.ch1.trace.LogTrace;
import com.springboot.advanced.ch3.v13.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

@Configuration
@RequiredArgsConstructor
public class ProxyV14Config {

    private final static String[] PATTERNS = {"request*", "order*", "save*"};

    @Bean
    public OrderControllerV14 orderControllerV14(LogTrace logTraceV5) {
        return (OrderControllerV14)Proxy.newProxyInstance(
                OrderControllerV14.class.getClassLoader(),
                new Class[]{OrderControllerV14.class},
                new LogInvocationFilterHandler(new OrderControllerV14Impl(orderServiceV14(logTraceV5)), logTraceV5, PATTERNS)
        );
    }

    @Bean
    public OrderServiceV14 orderServiceV14(LogTrace logTraceV5) {
        return (OrderServiceV14)Proxy.newProxyInstance(
                OrderServiceV14.class.getClassLoader(),
                new Class[]{OrderServiceV14.class},
                new LogInvocationFilterHandler(new OrderServiceV14Impl(orderRepositoryV14(logTraceV5)), logTraceV5, PATTERNS)
        );
    }

    @Bean
    public OrderRepositoryV14 orderRepositoryV14(LogTrace logTraceV5) {
        return (OrderRepositoryV14)Proxy.newProxyInstance(
                OrderRepositoryV14.class.getClassLoader(),
                new Class[]{OrderRepositoryV14.class},
                new LogInvocationFilterHandler(new OrderRepositoryV14Impl(), logTraceV5, PATTERNS)
        );
    }
}

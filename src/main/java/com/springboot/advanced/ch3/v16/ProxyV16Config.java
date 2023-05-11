package com.springboot.advanced.ch3.v16;

import com.springboot.advanced.ch1.trace.LogTrace;
import com.springboot.advanced.ch3.v15.LogAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ProxyV16Config {

    @Bean
    public OrderControllerV16 orderControllerV16(LogTrace logTraceV5) {
        OrderControllerV16 target = new OrderControllerV16Impl(orderServiceV16(logTraceV5));
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvisor(getAdvisor(logTraceV5));
        OrderControllerV16 proxy = (OrderControllerV16) proxyFactory.getProxy();

        log.info("ProxyFactory proxy: {}, target: {}", proxy.getClass(), target.getClass());

        return proxy;
    }

    @Bean
    public OrderServiceV16 orderServiceV16(LogTrace logTraceV5) {
        OrderServiceV16 target = new OrderServiceV16Impl(orderRepositoryV16(logTraceV5));
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvisor(getAdvisor(logTraceV5));
        OrderServiceV16 proxy = (OrderServiceV16) proxyFactory.getProxy();

        log.info("ProxyFactory proxy: {}, target: {}", proxy.getClass(), target.getClass());

        return proxy;
    }

    @Bean
    public OrderRepositoryV16 orderRepositoryV16(LogTrace logTraceV5) {
        OrderRepositoryV16 target = new OrderRepositoryV16Impl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvisor(getAdvisor(logTraceV5));
        OrderRepositoryV16 proxy = (OrderRepositoryV16) proxyFactory.getProxy();

        log.info("ProxyFactory proxy: {}, target: {}", proxy.getClass(), target.getClass());

        return proxy;
    }

    private Advisor getAdvisor(LogTrace logTraceV5) {
        // pointcut
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*", "save*");

        return new DefaultPointcutAdvisor(pointcut, new LogAdvice(logTraceV5));
    }
}

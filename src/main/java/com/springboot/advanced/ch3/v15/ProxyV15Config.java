package com.springboot.advanced.ch3.v15;

import com.springboot.advanced.ch1.trace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ProxyV15Config {

    @Bean
    public OrderControllerV15 orderControllerV15(LogTrace logTraceV5) {
        OrderControllerV15 target = new OrderControllerV15(orderServiceV15(logTraceV5));
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvisor(getAdvisor(logTraceV5));
        OrderControllerV15 proxy = (OrderControllerV15) proxyFactory.getProxy();

        log.info("ProxyFactory proxy: {}, target: {}", proxy.getClass(), target.getClass());

        return proxy;
    }

    @Bean
    public OrderServiceV15 orderServiceV15(LogTrace logTraceV5) {
        OrderServiceV15 target = new OrderServiceV15(orderRepositoryV15(logTraceV5));
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvisor(getAdvisor(logTraceV5));
        OrderServiceV15 proxy = (OrderServiceV15) proxyFactory.getProxy();

        log.info("ProxyFactory proxy: {}, target: {}", proxy.getClass(), target.getClass());

        return proxy;
    }

    @Bean
    public OrderRepositoryV15 orderRepositoryV15(LogTrace logTraceV5) {
        OrderRepositoryV15 target = new OrderRepositoryV15();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvisor(getAdvisor(logTraceV5));
        OrderRepositoryV15 proxy = (OrderRepositoryV15) proxyFactory.getProxy();

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

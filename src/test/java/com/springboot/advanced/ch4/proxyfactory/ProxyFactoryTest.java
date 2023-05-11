package com.springboot.advanced.ch4.proxyfactory;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class ProxyFactoryTest {
    @Test
    void createProxyWithInterfaceUsingJdkDynamicProxy() {
        ServiceInterface target = new ServiceImpl();
        ProxyFactory factory = new ProxyFactory(target);
        factory.addAdvice(new TimeMethodInterceptor());
        ServiceInterface proxiedService = (ServiceInterface) factory.getProxy();

        log.info("proxiedService: {}", proxiedService.getClass());

        assertTrue(AopUtils.isAopProxy(proxiedService));
        assertTrue(AopUtils.isJdkDynamicProxy(proxiedService));
        assertFalse(AopUtils.isCglibProxy(proxiedService));

        proxiedService.find();
        proxiedService.save();
    }

    @Test
    void createProxyWithoutInterfaceUsingCglib() {
        ConcreteService target = new ConcreteService();
        ProxyFactory factory = new ProxyFactory(target);
        factory.addAdvice(new TimeMethodInterceptor());

        ConcreteService proxiedService = (ConcreteService) factory.getProxy();

        log.info("proxiedService: {}", proxiedService.getClass());

        assertTrue(AopUtils.isAopProxy(proxiedService));
        assertFalse(AopUtils.isJdkDynamicProxy(proxiedService));
        assertTrue(AopUtils.isCglibProxy(proxiedService));

        proxiedService.find();
        proxiedService.save();
    }

    @Test
    void createProxyWithInterfaceUsingCglib() {
        ServiceInterface target = new ServiceImpl();
        ProxyFactory factory = new ProxyFactory(target);
        factory.setProxyTargetClass(true); // target 클래스(구체클래스)를 기반으로 프록시를 생성(cglib)하겠다는 의미이다.
        factory.addAdvice(new TimeMethodInterceptor());
        ServiceInterface proxiedService = (ServiceInterface) factory.getProxy();

        log.info("proxiedService: {}", proxiedService.getClass());

        assertTrue(AopUtils.isAopProxy(proxiedService));
        assertFalse(AopUtils.isJdkDynamicProxy(proxiedService));
        assertTrue(AopUtils.isCglibProxy(proxiedService));

        proxiedService.find();
        proxiedService.save();
    }
}

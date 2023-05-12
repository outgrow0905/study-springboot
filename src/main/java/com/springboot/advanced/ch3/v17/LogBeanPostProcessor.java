package com.springboot.advanced.ch3.v17;

import com.springboot.advanced.ch1.trace.LogTrace;
import com.springboot.advanced.ch3.v15.LogAdvice;
import com.springboot.advanced.ch3.v15.OrderRepositoryV15;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

@Slf4j
@RequiredArgsConstructor
public class LogBeanPostProcessor implements BeanPostProcessor {

    private final Advisor advisor;
    private final String basePackage;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (!bean.getClass().getPackageName().equals(basePackage)) {
            return bean;
        }

        ProxyFactory proxyFactory = new ProxyFactory(bean);
        proxyFactory.addAdvisor(advisor);
//        proxyFactory.setProxyTargetClass(true); // why orderRepositoryV17 is jdk proxy...?
        Object proxy = proxyFactory.getProxy();

        log.info("target: {}, proxy: {}", bean.getClass(), proxy.getClass());

        return proxy;
    }
}

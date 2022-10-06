package com.springboot.study.ch6.v10.proxyfactorybean;

import static org.junit.jupiter.api.Assertions.*;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactoryBean;

@Slf4j
class MySayTest {

    @Test
    public void proxyFactoryBean() {
        ProxyFactoryBean pfBean = new ProxyFactoryBean();
        pfBean.setTarget(new MySay());
        pfBean.addAdvice(new UppercaseAdvice());
        Say proxiedHello = (Say) pfBean.getObject();
        assertEquals("YEAH JUNE", proxiedHello.sayYeah("June"));
    }
    static class UppercaseAdvice implements MethodInterceptor {
        public Object invoke(MethodInvocation invocation) throws Throwable {
            String ret = (String)invocation.proceed();
            return ret.toUpperCase();
        }
    }
}
package com.springboot.aop.ch5.v1;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
class MemberServiceTest {
    @Test
    void jdkProxy() {
        MemberServiceV1Impl target = new MemberServiceV1Impl();
        ProxyFactory factory = new ProxyFactory(target);
        factory.setProxyTargetClass(false); // jdk proxy

        // cast to interface
        MemberServiceV1 memberServiceInterface = (MemberServiceV1)factory.getProxy();

        // can't cast to target class
        assertThrows(ClassCastException.class, () -> {
            MemberServiceV1Impl memberServiceImpl = (MemberServiceV1Impl)factory.getProxy();
        });
    }

    @Test
    void cglib() {
        MemberServiceV1Impl target = new MemberServiceV1Impl();
        ProxyFactory factory = new ProxyFactory(target);
        factory.setProxyTargetClass(true); // cglib

        // cast to interface
        MemberServiceV1 memberServiceInterface = (MemberServiceV1)factory.getProxy();

        // cast to target class
        MemberServiceV1Impl memberServiceImpl = (MemberServiceV1Impl)factory.getProxy();
    }
}
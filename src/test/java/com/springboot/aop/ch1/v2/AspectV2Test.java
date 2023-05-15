package com.springboot.aop.ch1.v2;

import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AspectV2Test {
    @Autowired
    private BillingServiceV2 billingServiceV2;

    @Autowired
    private BillingRepositoryV2 billingRepositoryV2;

    @Test
    void isAop() {
        assertTrue(AopUtils.isAopProxy(billingServiceV2));
        assertTrue(AopUtils.isAopProxy(billingRepositoryV2));
    }

    @Test
    void orderItem() {
        billingServiceV2.orderItem("itemA");
    }
}
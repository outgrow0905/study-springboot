package com.springboot.aop.ch1.v6;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AspectV6Test {
    @Autowired
    private BillingServiceV6 billingServiceV6;

    @Test
    void orderItem() {
        billingServiceV6.orderItem("itemA");
    }
}
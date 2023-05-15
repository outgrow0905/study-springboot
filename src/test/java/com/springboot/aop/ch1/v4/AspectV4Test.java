package com.springboot.aop.ch1.v4;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AspectV4Test {
    @Autowired
    private BillingServiceV4 billingServiceV4;

    @Autowired
    private BillingRepositoryV4 billingRepositoryV4;

    @Test
    void logService() {
        billingServiceV4.orderItem("itemA");
    }
}
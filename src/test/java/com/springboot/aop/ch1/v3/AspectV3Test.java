package com.springboot.aop.ch1.v3;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class AspectV3Test {
    @Autowired
    private BillingServiceV3 billingServiceV3;
    @Autowired
    private BillingRepositoryV3 billingRepositoryV3;

    @Test
    void orderItem() {
        billingServiceV3.orderItem("itemA");
    }
}
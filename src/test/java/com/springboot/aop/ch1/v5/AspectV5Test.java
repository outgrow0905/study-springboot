package com.springboot.aop.ch1.v5;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class AspectV5Test {
    @Autowired
    private BillingServiceV5 billingServiceV5;

    @Test
    void orderItem() {
        billingServiceV5.orderItem("itemA");
    }
}
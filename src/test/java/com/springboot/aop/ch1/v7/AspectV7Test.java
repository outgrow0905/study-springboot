package com.springboot.aop.ch1.v7;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class AspectV7Test {
    @Autowired
    private BillingServiceV7 billingServiceV7;

    @Test
    void orderItem() {
        billingServiceV7.orderItem("itemA");
    }

    @Test
    void orderItemEx() {
        assertThrows(IllegalStateException.class, () -> billingServiceV7.orderItem("ex"));
    }
}
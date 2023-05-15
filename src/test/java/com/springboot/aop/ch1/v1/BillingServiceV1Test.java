package com.springboot.aop.ch1.v1;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class BillingServiceV1Test {
    @Autowired
    private BillingServiceV1 billingServiceV1;

    @Autowired
    private BillingRepositoryV1 billingRepositoryV1;

    @Test
    void aopInfo() {
        log.info("isAop: {}", AopUtils.isAopProxy(billingServiceV1)); // false
        log.info("isAop: {}", AopUtils.isAopProxy(billingRepositoryV1)); // true, spring-jdbc 라이브러리로 자동등록
    }

    @Test
    void orderServiceSuccess() {
        billingServiceV1.orderItem("itemA");
    }

    @Test
    void orderServiceFail() {
        assertThrows(
                IllegalStateException.class,
                () -> billingServiceV1.orderItem("ex"));
    }
}
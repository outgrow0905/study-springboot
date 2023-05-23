package com.springboot.aop.ch4.v3;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CallServiceV3Test {
    @Autowired
    CallServiceV3 callService;

    @Test
    void external() {
        callService.external();
    }
}
package com.springboot.aop.ch4.v2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CallServiceV2Test {

    @Autowired
    CallServiceV2 callService;

    @Test
    void external() {
        callService.external();
    }
}
package com.springboot.aop.ch4.v5;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CallServiceV5Test {

    @Autowired
    CallServiceV5 callService;

    @Test
    void external() {
        callService.external();
    }
}
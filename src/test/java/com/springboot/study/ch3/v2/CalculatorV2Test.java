package com.springboot.study.ch3.v2;

import static org.junit.jupiter.api.Assertions.*;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class CalculatorV2Test {

    @Autowired
    private CalculatorV2 calculatorV2;

    @Test
    void add() {
        assertEquals(55, calculatorV2.add("numbers.txt"));
    }

    @Test
    void multiply() {
        assertEquals(3628800, calculatorV2.multiply("numbers.txt"));
    }
}
package com.springboot.study.ch3.v3;

import static org.junit.jupiter.api.Assertions.*;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class CalculatorV3Test {

    @Autowired
    private CalculatorV3 calculatorV3;

    @Test
    void add() {
      assertEquals(55, calculatorV3.add("numbers.txt"));
    }

    @Test
    void multiply() {
      assertEquals(3628800, calculatorV3.multiply("numbers.txt"));
    }
}
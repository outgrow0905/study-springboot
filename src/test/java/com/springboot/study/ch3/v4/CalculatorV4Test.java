package com.springboot.study.ch3.v4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.springboot.study.ch3.v3.CalculatorV3;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class CalculatorV4Test {

    @Autowired
    private CalculatorV4 calculatorV4;

    @Test
    void add() {
      assertEquals(55, calculatorV4.add("numbers.txt", 0));
    }

    @Test
    void multiply() {
      assertEquals(3628800, calculatorV4.multiply("numbers.txt", 1));
    }

    @Test
    void concat() {
      assertEquals("12345678910", calculatorV4.concat("numbers.txt", Strings.EMPTY));
    }
}
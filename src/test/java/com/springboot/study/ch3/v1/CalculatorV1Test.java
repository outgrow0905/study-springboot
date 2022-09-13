package com.springboot.study.ch3.v1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class CalculatorV1Test {

    @Autowired
    private CalculatorV1 calculatorV1;

    @Test
    void add() throws Exception {
      BufferedReader bufferedReader = null;
      try {
            bufferedReader = new BufferedReader(new FileReader("src/main/resources/ch3/numbers.txt"));

            Integer result = null;
            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                if (null == result) {
                    result = Integer.valueOf(line);
                } else {
                    result += Integer.valueOf(line);
                }
            }

            log.info("result: {}", result);

        } catch (FileNotFoundException  e) {
            log.error(e.getMessage());
        } finally {
            try {
                if (null != bufferedReader) {
                    bufferedReader.close();
                }
            } catch (IOException e) {}
        }
    }

    @Test
    void multiply() throws Exception {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader("src/main/resources/ch3/numbers.txt"));

            Integer result = null;
            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                if (null == result) {
                    result = Integer.valueOf(line);
                } else {
                    result *= Integer.valueOf(line);
                }
            }

            log.info("result: {}", result);

        } catch (FileNotFoundException  e) {
            log.error(e.getMessage());
        } finally {
            try {
                if (null != bufferedReader) {
                    bufferedReader.close();
                }
            } catch (IOException e) {}
        }
    }
}
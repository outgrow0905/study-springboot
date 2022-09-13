package com.springboot.study.ch3.v1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CalculatorV1 {

    public Integer add(final String filepath) {
        BufferedReader bufferedReader = null;
        Integer result = null;
        try {
            bufferedReader = new BufferedReader(
                new FileReader(String.format("./src/main/resources/ch3/%s", filepath)));

            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                if (null == result) {
                    result = Integer.valueOf(line);
                } else {
                    result += Integer.valueOf(line);
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            try {
                if (null != bufferedReader) {
                    bufferedReader.close();
                }
            } catch (IOException e) {}
        }

        log.info("result: {}", result);
        return result;
    }

    public Integer multiply(final String filepath) {
        BufferedReader bufferedReader = null;
        Integer result = null;
        try {
            bufferedReader = new BufferedReader(
                new FileReader(String.format("./src/main/resources/ch3/%s", filepath)));

            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                if (null == result) {
                    result = Integer.valueOf(line);
                } else {
                    result *= Integer.valueOf(line);
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            try {
                if (null != bufferedReader) {
                    bufferedReader.close();
                }
            } catch (IOException e) {}
        }

        log.info("result: {}", result);
        return result;
    }
}

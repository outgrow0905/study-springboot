package com.springboot.study.ch3.v2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CalculatorV2 {

    private final CalculatorContextV2 context;

    public Integer add(final String filepath) {
        return context.calculate(filepath, bufferedReader -> {
            String line = null;
            Integer result = null;

            while ((line = bufferedReader.readLine()) != null) {
                if (null == result) {
                    result = Integer.valueOf(line);
                } else {
                    result += Integer.valueOf(line);
                }
            }

            return result;
        });
    }

    public Integer multiply(final String filepath) {
        return context.calculate(filepath, bufferedReader -> {
            String line = null;
            Integer result = null;

            while ((line = bufferedReader.readLine()) != null) {
                if (null == result) {
                    result = Integer.valueOf(line);
                } else {
                    result *= Integer.valueOf(line);
                }
            }

            return result;
        });
    }
}

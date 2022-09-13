package com.springboot.study.ch3.v4;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CalculatorV4 {

    private final CalculatorContextV4 context;

    public Integer add(final String filepath, int initValue) {
        return context.calculate(filepath, (line, result) -> line + result, initValue);
    }

    public Integer multiply(final String filepath, int initValue) {
        return context.calculate(filepath, (line, result) -> line * result, initValue);
    }

    public String concat(final String filepath, String initValue) {
        return context.calculate(filepath, (line, result) -> result.concat(String.valueOf(line)), Strings.EMPTY);
    }
}

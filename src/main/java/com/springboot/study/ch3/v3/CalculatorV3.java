package com.springboot.study.ch3.v3;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CalculatorV3 {

    private final CalculatorContextV3 context;

    public Integer add(final String filepath) {
        return context.calculate(filepath, (line, result) -> line + result);
    }

    public Integer multiply(final String filepath) {
        return context.calculate(filepath, (line, result) -> line * result);
    }
}

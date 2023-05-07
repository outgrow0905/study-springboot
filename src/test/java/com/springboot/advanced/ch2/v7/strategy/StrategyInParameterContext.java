package com.springboot.advanced.ch2.v7.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StrategyInParameterContext {
    public void execute(Strategy strategy) {
        long startMs = System.currentTimeMillis();

        // business logic start..
        strategy.call();
        // business logic end..

        long endMs = System.currentTimeMillis();
        long boxUsage = endMs - startMs;
        log.info("boxUsage: {}", boxUsage);
    }
}

package com.springboot.advanced.ch2.v7.strategy;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
public class StrategyInFieldContext {

    private Strategy strategy;

    public StrategyInFieldContext() {
    }

    public StrategyInFieldContext(Strategy strategy) {
        this.strategy = strategy;
    }

    public void execute() {
        long startMs = System.currentTimeMillis();

        // business logic start..
        strategy.call();
        // business logic end..

        long endMs = System.currentTimeMillis();
        long boxUsage = endMs - startMs;
        log.info("boxUsage: {}", boxUsage);
    }


}

package com.springboot.advanced.ch2.v7.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StrategyLogic2 implements Strategy {
    @Override
    public void call() {
        log.info("logic 1 execute..");
    }
}

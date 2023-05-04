package com.springboot.advanced.ch2.v1.template;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTemplate {
    public void execute() {
        long startMs = System.currentTimeMillis();

        // business logic start..
        call();
        // business logic end..

        long endMs = System.currentTimeMillis();
        long boxUsage = endMs - startMs;
        log.info("boxUsage: {}", boxUsage);
    }

    protected abstract void call();
}

package com.springboot.advanced.ch3.decorator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeDecorator extends Decorator {
    public TimeDecorator(Component component) {
        super(component);
    }

    @Override
    public String operation() {
        long startMs = System.currentTimeMillis();
        String result = component.operation();
        long endMs = System.currentTimeMillis();
        log.debug("boxUsage: {}", endMs - startMs);
        return result;
    }
}

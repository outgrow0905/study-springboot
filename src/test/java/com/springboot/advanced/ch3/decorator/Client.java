package com.springboot.advanced.ch3.decorator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Client {
    private final Component component;

    public Client(Component component) {
        this.component = component;
    }

    public void execute() {
        String result = component.operation();
        log.debug("result: {}", result);
    }
}

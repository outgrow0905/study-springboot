package com.springboot.advanced.ch3.v12;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConcreteLogic {
    public String operation() {
        log.info("ConcreteLogic operation()");
        return "data";
    }
}

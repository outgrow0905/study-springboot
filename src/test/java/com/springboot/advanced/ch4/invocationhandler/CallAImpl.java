package com.springboot.advanced.ch4.invocationhandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CallAImpl implements CallA {
    @Override
    public String call() {
        log.info("CallA call()");
        return "A";
    }
}

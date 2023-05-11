package com.springboot.advanced.ch4.invocationhandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CallBImpl implements CallB {
    @Override
    public String call() {
        log.info("CallB call()");
        return "B";
    }
}

package com.springboot.aop.ch4.v1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CallServiceV1 {
    public void external() {
        log.info("external call()");
        internal();
    }

    public void internal() {
        log.info("internal call()");
    }
}

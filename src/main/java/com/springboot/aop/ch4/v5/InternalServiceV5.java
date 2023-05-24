package com.springboot.aop.ch4.v5;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class InternalServiceV5 {
    public void internal() {
        log.info("internal call()");
    }
}

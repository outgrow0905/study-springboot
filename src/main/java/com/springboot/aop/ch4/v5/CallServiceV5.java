package com.springboot.aop.ch4.v5;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CallServiceV5 {

    private final InternalServiceV5 internalService;

    public void external() {
        log.info("external call()");
        internalService.internal();
    }
}

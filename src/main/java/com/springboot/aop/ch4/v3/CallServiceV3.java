package com.springboot.aop.ch4.v3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CallServiceV3 {

    private final ApplicationContext context;

    public CallServiceV3(ApplicationContext context) {
        this.context = context;
    }

    public void external() {
        log.info("external call()");
        CallServiceV3 callService = context.getBean(getClass());
        callService.internal();
    }

    public void internal() {
        log.info("internal call()");
    }


}

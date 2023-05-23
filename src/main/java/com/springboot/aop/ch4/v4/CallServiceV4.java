package com.springboot.aop.ch4.v4;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CallServiceV4 {

    private final ObjectProvider<CallServiceV4> provider;

    public CallServiceV4(ObjectProvider<CallServiceV4> provider) {
        this.provider = provider;
    }

    public void external() {
        log.info("external call()");
        CallServiceV4 callService = provider.getObject();
        callService.internal();
    }

    public void internal() {
        log.info("internal call()");
    }


}

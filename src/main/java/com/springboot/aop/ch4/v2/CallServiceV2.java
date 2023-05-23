package com.springboot.aop.ch4.v2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class CallServiceV2 {

    private CallServiceV2 callService; // spring.main.allow-circular-references=true

    @Autowired
    public void setCallService(CallServiceV2 callService) {
        this.callService = callService;
    }

    public void external() {
        log.info("external call()");
        callService.internal();
    }

    public void internal() {
        log.info("internal call()");
    }


}

package com.springboot.aop.ch4.v1;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class CallLogAspectV1 {
    @Before("execution(* com.springboot.aop.ch4.v1..*(..))")
    public void doLog(JoinPoint joinPoint) {
        log.info("aop:{}", joinPoint.getSignature());
    }
}

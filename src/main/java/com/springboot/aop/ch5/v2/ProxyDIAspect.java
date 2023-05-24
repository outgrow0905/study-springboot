package com.springboot.aop.ch5.v2;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ProxyDIAspect {
    @Before("execution(* com.springboot.aop.ch5.v2..*.*(..))")
    public void doTrace(JoinPoint joinPoint) {
        log.info("[ProxyDIAspect] {}", joinPoint.getSignature());
    }
}

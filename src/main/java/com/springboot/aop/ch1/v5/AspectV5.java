package com.springboot.aop.ch1.v5;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class AspectV5 {
    @Around("com.springboot.aop.ch1.v5.PointcutsV5.logV5Package()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature());
        return joinPoint.proceed();
    }

    @Around("com.springboot.aop.ch1.v5.PointcutsV5.logServiceV5InV5Package()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            log.info("[transaction/start] {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            log.info("[transaction/commit] {}", joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            log.info("[transaction/rollback] {}", joinPoint.getSignature());
            throw e;
        } finally {
            log.info("[transaction/resource release] {}", joinPoint.getSignature());
        }
    }
}

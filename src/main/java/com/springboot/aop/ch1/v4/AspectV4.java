package com.springboot.aop.ch1.v4;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class AspectV4 {

    @Pointcut("execution(* com.springboot.aop.ch1.v4..*(..))")
    private void logAll(){}


    @Pointcut("execution(* *..*ServiceV4.*(..))")
    private void logServiceInV4(){}

    @Around("logAll()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature());
        return joinPoint.proceed();
    }

    @Around("logAll() && logServiceInV4()")
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

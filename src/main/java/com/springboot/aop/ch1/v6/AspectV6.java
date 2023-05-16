package com.springboot.aop.ch1.v6;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
public class AspectV6 {

    @Aspect
    @Component
    @Order(2)
    public static class LogAspectV6 {
        @Around("com.springboot.aop.ch1.v6.PointcutsV6.logV6Package()")
        public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[log] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }

    @Aspect
    @Component
    @Order(1)
    public static class TxAspectV6 {
        @Around("com.springboot.aop.ch1.v6.PointcutsV6.logServiceV6InV6Package()")
        public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
            try {
                // @Before
                log.info("[transaction/start] {}", joinPoint.getSignature());
                Object result = joinPoint.proceed();
                // @AfterReturning
                log.info("[transaction/commit] {}", joinPoint.getSignature());
                return result;
            } catch (Exception e) {
                // @AfterThrowing
                log.info("[transaction/rollback] {}", joinPoint.getSignature());
                throw e;
            } finally {
                // @After
                log.info("[transaction/resource release] {}", joinPoint.getSignature());
            }
        }
    }
}

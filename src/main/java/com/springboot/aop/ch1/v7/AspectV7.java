package com.springboot.aop.ch1.v7;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class AspectV7 {
//    @Around("com.springboot.aop.ch1.v7.PointcutsV7.logServiceV7InV7Package()")
//    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
//        try {
//            // @Before
//            log.info("[transaction/start] {}", joinPoint.getSignature());
//            Object result = joinPoint.proceed();
//            // @AfterReturning
//            log.info("[transaction/commit] {}", joinPoint.getSignature());
//            return result;
//        } catch (Exception e) {
//            // @AfterThrowing
//            log.info("[transaction/rollback] {}", joinPoint.getSignature());
//            throw e;
//        } finally {
//            // @After
//            log.info("[transaction/resource release] {}", joinPoint.getSignature());
//        }
//    }

    @Before("com.springboot.aop.ch1.v7.PointcutsV7.logServiceV7InV7Package()")
    public void before(JoinPoint joinPoint) throws Throwable {
        log.info("[transaction/start] {}", joinPoint.getSignature());
    }

    @AfterReturning(value = "com.springboot.aop.ch1.v7.PointcutsV7.logServiceV7InV7Package()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        log.info("[transaction/commit] {} {}", joinPoint.getSignature(), result);
    }

    @After("com.springboot.aop.ch1.v7.PointcutsV7.logServiceV7InV7Package()")
    public void after(JoinPoint joinPoint) {
        log.info("[transaction/resource release] {}", joinPoint.getSignature());
    }

    @AfterThrowing(value = "com.springboot.aop.ch1.v7.PointcutsV7.logServiceV7InV7Package()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        log.info("[transaction/rollback] {} {}", joinPoint.getSignature(), ex);
    }

}

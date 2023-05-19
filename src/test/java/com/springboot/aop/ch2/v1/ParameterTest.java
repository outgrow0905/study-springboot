package com.springboot.aop.ch2.v1;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Slf4j
@SpringBootTest
@Import(ParameterTest.ParameterAspect.class)
public class ParameterTest {

    @Autowired
    MemberServiceV1 memberService;

    @Test
    void success() {
        log.info("memberService Proxy: {}", memberService.getClass());
        memberService.hello("helloA");
    }

    @Slf4j
    @Aspect
    static class ParameterAspect {
        @Pointcut("execution(* com.springboot.aop.ch2.v1..*(..))")
        private void allMember() {}

        @Around("allMember()")
        public Object logArgs1(ProceedingJoinPoint joinPoint) throws Throwable {
            Object arg = joinPoint.getArgs()[0];
            log.info("[logArgs1] {}, arg: {}", joinPoint.getSignature(), arg);
            return joinPoint.proceed();
        }

        @Around("allMember() && args(arg, ..)")
        public Object logArgs2(ProceedingJoinPoint joinPoint, Object arg) throws Throwable {
            log.info("[logArgs2] {}, arg: {}", joinPoint.getSignature(), arg);
            return joinPoint.proceed();
        }

        @Before("allMember() && args(arg, ..)")
        public void logArgs3(String arg) {
            log.info("[logArgs3] arg: {}", arg);
        }

        @Before("allMember() && this(obj)")
        public void logThis(JoinPoint joinPoint, MemberServiceV1 obj) {
            // proxy instance
            log.info("[logThis] {}, obj: {}", joinPoint.getSignature(), obj.getClass());
        }

        @Before("allMember() && target(obj)")
        public void logTarget(JoinPoint joinPoint, MemberServiceV1 obj) {
            // target instance
            log.info("[logTarget] {}, obj: {}", joinPoint.getSignature(), obj.getClass());
        }

        @Before("allMember() && target(obj1) && this(obj2)")
        public void logTargetAndThis(JoinPoint joinPoint, MemberServiceV1 obj1, MemberServiceV1 obj2) {
            // target And instance
            log.info("[logTargetAndThis] {}, obj1: {}, obj2: {}", joinPoint.getSignature(), obj1.getClass(), obj2.getClass());
        }

        @Before("allMember() && @target(annotation)")
        public void logTarget(JoinPoint joinPoint, ClassAop annotation) {
            log.info("[@target] {}, annotation: {}", joinPoint.getSignature(), annotation);
        }

        @Before("allMember() && @within(annotation)")
        public void logWithin(JoinPoint joinPoint, ClassAop annotation) {
            log.info("[@within] {}, annotation: {}", joinPoint.getSignature(), annotation);
        }

        @Before("allMember() && @annotation(annotation)")
        public void logAnnotation(JoinPoint joinPoint, MethodAop annotation) {
            log.info("[@annotation] {}, annotation: {}, value: {}", joinPoint.getSignature(), annotation, annotation.value());
        }
    }
}

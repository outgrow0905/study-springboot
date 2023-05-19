package com.springboot.aop.ch2.v1;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

@Slf4j
//@SpringBootTest // cglib (spring default)
@SpringBootTest(properties = "spring.aop.proxy-target-class=false") // jdk proxy
@Import({ThisTargetTest.ThisTargetAspect.class})
public class ThisTargetTest {
    @Autowired
    MemberServiceV1 memberService;

    @Test
    void success() {
        log.info("Proxy type: {}", memberService.getClass());
        memberService.hello("halloA");
    }

    @Slf4j
    @Aspect
    static class ThisTargetAspect {

        @Around("this(com.springboot.aop.ch2.v1.MemberServiceV1)")
        public Object doThisInterface(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[this-interface] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

        @Around("target(com.springboot.aop.ch2.v1.MemberServiceV1)")
        public Object doTargetInterface(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[target-interface] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

        @Around("this(com.springboot.aop.ch2.v1.MemberServiceV1Impl)")
        public Object doThis(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[this-impl] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

        @Around("target(com.springboot.aop.ch2.v1.MemberServiceV1Impl)")
        public Object doTarget(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[target-impl] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }
}

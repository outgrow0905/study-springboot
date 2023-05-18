package com.springboot.aop.ch2.v1;

import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Import(AtAnnotationTest.AtAnnotationAspect.class) // @Import 는 빈 등록을 같이 해준다.
public class AtAnnotationTest {

    @Autowired
    MemberServiceV1 memberServiceV1;

    @Test
    void success() {
        log.info("memberServiceV1: {}", memberServiceV1.getClass());
        assertEquals("ok", memberServiceV1.hello("helloA"));
    }

    @Slf4j
    @Aspect
    static class AtAnnotationAspect {
        @Around("@annotation(com.springboot.aop.ch2.v1.MethodAop)")
        public Object doAtAnnotation(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[@annotation] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }
}

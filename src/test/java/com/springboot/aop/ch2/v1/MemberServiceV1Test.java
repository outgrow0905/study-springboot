package com.springboot.aop.ch2.v1;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class MemberServiceV1Test {

    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method helloMethod;

    @BeforeEach
    void init() throws NoSuchMethodException {
        helloMethod = MemberServiceV1Impl.class.getMethod("hello", String.class);
    }

    @Test
    void printMethod() {
        // public java.lang.String com.springboot.aop.ch2.v1.MemberServiceV1Impl.hello(java.lang.String)
        log.info("helloMethod: {}", helloMethod);
    }

    @Test
    void exactMatch() {
        pointcut.setExpression("execution(public java.lang.String com.springboot.aop.ch2.v1.MemberServiceV1Impl.hello(java.lang.String))");
        assertTrue(pointcut.matches(helloMethod, MemberServiceV1Impl.class));

        pointcut.setExpression("execution(public String com.springboot.aop.ch2.v1.MemberServiceV1Impl.hello(String))");
        assertTrue(pointcut.matches(helloMethod, MemberServiceV1Impl.class));
    }

    @Test
    void allMatch() {
        pointcut.setExpression("execution(* *(..))");
        assertTrue(pointcut.matches(helloMethod, MemberServiceV1Impl.class));
    }

    @Test
    void nameMatch() {
        pointcut.setExpression("execution(* hello(..))");
        assertTrue(pointcut.matches(helloMethod, MemberServiceV1Impl.class));
    }

    @Test
    void patternMatch() {
        pointcut.setExpression("execution(* hel*(..))");
        assertTrue(pointcut.matches(helloMethod, MemberServiceV1Impl.class));

        pointcut.setExpression("execution(* *el*(..))");
        assertTrue(pointcut.matches(helloMethod, MemberServiceV1Impl.class));
    }

    @Test
    void nameMatchFalse() {
        pointcut.setExpression("execution(* hell0(..))");
        assertFalse(pointcut.matches(helloMethod, MemberServiceV1Impl.class));
    }

    @Test
    void packageExactMatch1() {
        pointcut.setExpression("execution(* com.springboot.aop.ch2.v1.MemberServiceV1Impl.hello(..))");
        assertTrue(pointcut.matches(helloMethod, MemberServiceV1Impl.class));
    }

    @Test
    void packageExactMatch2() {
        pointcut.setExpression("execution(* com.springboot.aop.ch2.v1.*.*(..))");
        assertTrue(pointcut.matches(helloMethod, MemberServiceV1Impl.class));
    }

    @Test
    void packageExactMatch3() {
        pointcut.setExpression("execution(* com.springboot.aop.ch2.v1..*(..))");
        assertTrue(pointcut.matches(helloMethod, MemberServiceV1Impl.class));
    }

    @Test
    void typeExactMatch() {
        pointcut.setExpression("execution(* com.springboot.aop.ch2.v1.MemberServiceV1Impl.*(..))");
        assertTrue(pointcut.matches(helloMethod, MemberServiceV1Impl.class));
    }

    @Test
    void superTypeMatch() {
        pointcut.setExpression("execution(* com.springboot.aop.ch2.v1.MemberServiceV1.*(..))");
        assertTrue(pointcut.matches(helloMethod, MemberServiceV1Impl.class));
    }

    @Test
    void internalMatch1() throws NoSuchMethodException {
        Method internal = MemberServiceV1Impl.class.getMethod("internalHello", String.class);
        pointcut.setExpression("execution(* com.springboot.aop.ch2.v1.MemberServiceV1Impl.*(..))");
        assertTrue(pointcut.matches(internal, MemberServiceV1Impl.class));
    }

    @Test
    void internalMatch2() throws NoSuchMethodException {
        Method internal = MemberServiceV1Impl.class.getMethod("internalHello", String.class);
        pointcut.setExpression("execution(* com.springboot.aop.ch2.v1.MemberServiceV1.*(..))");
        assertFalse(pointcut.matches(internal, MemberServiceV1Impl.class));
    }

    @Test
    void argsMatch1() {
        pointcut.setExpression("execution(* *(String))");
        assertTrue(pointcut.matches(helloMethod, MemberServiceV1Impl.class));
    }

    @Test
    void argsMatch2() {
        pointcut.setExpression("execution(* *())");
        assertFalse(pointcut.matches(helloMethod, MemberServiceV1Impl.class));
    }

    @Test
    void argsMatch3() {
        pointcut.setExpression("execution(* *(*))"); // 하나의 파라미터, 모든 타입허용
        assertTrue(pointcut.matches(helloMethod, MemberServiceV1Impl.class));
    }

    @Test
    void argsMatch4() {
        pointcut.setExpression("execution(* *(..))"); // N개의 파라미터, 모든 타입허용
        assertTrue(pointcut.matches(helloMethod, MemberServiceV1Impl.class));
    }

    @Test
    void argsMatch5() {
        pointcut.setExpression("execution(* *(String, ..))"); // String으로 시작하는 파라미터, 이후는 N개의 모든 타입 파라미터 허용
        assertTrue(pointcut.matches(helloMethod, MemberServiceV1Impl.class));
    }
}
package com.springboot.aop.ch2.v1;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class ExecutionTest {

    AspectJExpressionPointcut pointcut;
    Method helloMethod;

    @BeforeEach
    void init() throws NoSuchMethodException {
        helloMethod = MemberServiceV1Impl.class.getMethod("hello", String.class);
    }

    private AspectJExpressionPointcut pointcut(String expression) {
        pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(expression);
        return pointcut;
    }

    @Test
    void printMethod() {
        // public java.lang.String com.springboot.aop.ch2.v1.MemberServiceV1Impl.hello(java.lang.String)
        log.info("helloMethod: {}", helloMethod);
    }

    @Test
    void exactMatch() {
        pointcut("execution(public java.lang.String com.springboot.aop.ch2.v1.MemberServiceV1Impl.hello(java.lang.String))");
        assertTrue(pointcut.matches(helloMethod, MemberServiceV1Impl.class));

        pointcut("execution(public String com.springboot.aop.ch2.v1.MemberServiceV1Impl.hello(String))");
        assertTrue(pointcut.matches(helloMethod, MemberServiceV1Impl.class));
    }

    @Test
    void allMatch() {
        pointcut("execution(* *(..))");
        assertTrue(pointcut.matches(helloMethod, MemberServiceV1Impl.class));
    }

    @Test
    void nameMatch() {
        pointcut("execution(* hello(..))");
        assertTrue(pointcut.matches(helloMethod, MemberServiceV1Impl.class));
    }

    @Test
    void patternMatch() {
        pointcut("execution(* hel*(..))");
        assertTrue(pointcut.matches(helloMethod, MemberServiceV1Impl.class));

        pointcut("execution(* *el*(..))");
        assertTrue(pointcut.matches(helloMethod, MemberServiceV1Impl.class));
    }

    @Test
    void nameMatchFalse() {
        pointcut("execution(* hell0(..))");
        assertFalse(pointcut.matches(helloMethod, MemberServiceV1Impl.class));
    }

    @Test
    void packageExactMatch1() {
        pointcut("execution(* com.springboot.aop.ch2.v1.MemberServiceV1Impl.hello(..))");
        assertTrue(pointcut.matches(helloMethod, MemberServiceV1Impl.class));
    }

    @Test
    void packageExactMatch2() {
        pointcut("execution(* com.springboot.aop.ch2.v1.*.*(..))");
        assertTrue(pointcut.matches(helloMethod, MemberServiceV1Impl.class));
    }

    @Test
    void packageExactMatch3() {
        pointcut("execution(* com.springboot.aop.ch2.v1..*(..))");
        assertTrue(pointcut.matches(helloMethod, MemberServiceV1Impl.class));
    }

    @Test
    void typeExactMatch() {
        pointcut("execution(* com.springboot.aop.ch2.v1.MemberServiceV1Impl.*(..))");
        assertTrue(pointcut.matches(helloMethod, MemberServiceV1Impl.class));
    }

    @Test
    void superTypeMatch() {
        pointcut("execution(* com.springboot.aop.ch2.v1.MemberServiceV1.*(..))");
        assertTrue(pointcut.matches(helloMethod, MemberServiceV1Impl.class));
    }

    @Test
    void internalMatch1() throws NoSuchMethodException {
        Method internal = MemberServiceV1Impl.class.getMethod("internalHello", String.class);
        pointcut("execution(* com.springboot.aop.ch2.v1.MemberServiceV1Impl.*(..))");
        assertTrue(pointcut.matches(internal, MemberServiceV1Impl.class));
    }

    @Test
    void internalMatch2() throws NoSuchMethodException {
        Method internal = MemberServiceV1Impl.class.getMethod("internalHello", String.class);
        pointcut("execution(* com.springboot.aop.ch2.v1.MemberServiceV1.*(..))");
        assertFalse(pointcut.matches(internal, MemberServiceV1Impl.class));
    }

    @Test
    void argsMatch1() {
        pointcut("execution(* *(String))");
        assertTrue(pointcut.matches(helloMethod, MemberServiceV1Impl.class));
    }

    @Test
    void argsMatch2() {
        pointcut("execution(* *())");
        assertFalse(pointcut.matches(helloMethod, MemberServiceV1Impl.class));
    }

    @Test
    void argsMatch3() {
        pointcut("execution(* *(*))"); // 하나의 파라미터, 모든 타입허용
        assertTrue(pointcut.matches(helloMethod, MemberServiceV1Impl.class));
    }

    @Test
    void argsMatch4() {
        pointcut("execution(* *(..))"); // N개의 파라미터, 모든 타입허용
        assertTrue(pointcut.matches(helloMethod, MemberServiceV1Impl.class));
    }

    @Test
    void argsMatch5() {
        pointcut("execution(* *(String, ..))"); // String으로 시작하는 파라미터, 이후는 N개의 모든 타입 파라미터 허용
        assertTrue(pointcut.matches(helloMethod, MemberServiceV1Impl.class));
    }

    /**
     * args 부분에서 부모타입을 허용하지 않는다.
     * 이부분은 args 문법을 사용하면 부모타입을 허용하는 부분과 다르다.
     * 그리고 execution과 within의 타입비교는 execution이 더 포괄적인데
     * execution과 args의 파라미터비교는 args가 더 포괄적이다.
     */
    @Test
    void argsMatch6() {
        pointcut("execution(* *(Object))");
        assertFalse(pointcut.matches(helloMethod, MemberServiceV1Impl.class));
    }
}
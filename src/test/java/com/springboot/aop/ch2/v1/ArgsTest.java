package com.springboot.aop.ch2.v1;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;
import static org.junit.jupiter.api.Assertions.*;
@Slf4j
public class ArgsTest {
    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
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
    void args() {
        assertTrue(pointcut("args(String)").matches(helloMethod, MemberServiceV1Impl.class));
        assertTrue(pointcut("args(Object)").matches(helloMethod, MemberServiceV1Impl.class)); // execution과 달리 파라미터에서 superType을 허용한다.
        assertFalse(pointcut("args()").matches(helloMethod, MemberServiceV1Impl.class));
        assertTrue(pointcut("args(..)").matches(helloMethod, MemberServiceV1Impl.class));
        assertTrue(pointcut("args(*)").matches(helloMethod, MemberServiceV1Impl.class));
        assertTrue(pointcut("args(String, ..)").matches(helloMethod, MemberServiceV1Impl.class));
    }

    @Test
    void argsVsExecution() {
        // args
        assertTrue(pointcut("args(String)").matches(helloMethod, MemberServiceV1Impl.class));
        assertTrue(pointcut("args(java.io.Serializable)").matches(helloMethod, MemberServiceV1Impl.class));
        assertTrue(pointcut("args(CharSequence)").matches(helloMethod, MemberServiceV1Impl.class));
        assertTrue(pointcut("args(Object)").matches(helloMethod, MemberServiceV1Impl.class));

        // execution
        assertTrue(pointcut("execution(public String com.springboot.aop.ch2.v1.MemberServiceV1Impl.hello(String))").matches(helloMethod, MemberServiceV1Impl.class));
        assertFalse(pointcut("execution(public String com.springboot.aop.ch2.v1.MemberServiceV1Impl.hello(java.io.Serializable))").matches(helloMethod, MemberServiceV1Impl.class));
        assertFalse(pointcut("execution(public String com.springboot.aop.ch2.v1.MemberServiceV1Impl.hello(CharSequence))").matches(helloMethod, MemberServiceV1Impl.class));
        assertFalse(pointcut("execution(public String com.springboot.aop.ch2.v1.MemberServiceV1Impl.hello(Object))").matches(helloMethod, MemberServiceV1Impl.class));
    }
}

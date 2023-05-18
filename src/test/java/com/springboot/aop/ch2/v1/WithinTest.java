package com.springboot.aop.ch2.v1;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class WithinTest {
    AspectJExpressionPointcut pointcut;
    Method helloMethod;

    private AspectJExpressionPointcut pointcut(String expression) {
        pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(expression);
        return pointcut;
    }

    @BeforeEach
    void init() throws NoSuchMethodException {
        helloMethod = MemberServiceV1Impl.class.getMethod("hello", String.class);
    }

    @Test
    void withinExact() {
        pointcut("within(com.springboot.aop.ch2.v1.MemberServiceV1Impl)");
        assertTrue(pointcut.matches(helloMethod, MemberServiceV1Impl.class));
    }

    @Test
    void withinStar1() {
        pointcut("within(com.springboot.aop.ch2.v1.MemberServiceV1*)");
        assertTrue(pointcut.matches(helloMethod, MemberServiceV1Impl.class));
    }

    @Test
    void withinStar2() {
        pointcut("within(com.springboot.aop.ch2.v1.*ServiceV1*)");
        assertTrue(pointcut.matches(helloMethod, MemberServiceV1Impl.class));
    }

    @Test
    void withinStar3() {
        pointcut("within(com.springboot.aop.ch2.*.*ServiceV1*)");
        assertTrue(pointcut.matches(helloMethod, MemberServiceV1Impl.class));
    }

    @Test
    void withinStar4() {
        pointcut("within(com.springboot.aop.ch2..*)");
        assertTrue(pointcut.matches(helloMethod, MemberServiceV1Impl.class));
    }

    /**
     * execution과 달리 인터페이스를 지정하면 매치되지 않는다.
     */
    @Test
    void withinSuperType() {
        // within
        pointcut("within(com.springboot.aop.ch2.v1.MemberServiceV1)");
        assertFalse(pointcut.matches(helloMethod, MemberServiceV1Impl.class));

        // execution
        pointcut("execution(* com.springboot.aop.ch2.v1.MemberServiceV1.*(..))");
        assertTrue(pointcut.matches(helloMethod, MemberServiceV1Impl.class));
    }
}

package com.springboot.advanced.ch4.invocationhandler;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

@Slf4j
public class InvocationHandlerTest {
    @Test
    void dynamicA() {
        CallA target = new CallAImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        CallA proxiedCallA =
                (CallA) Proxy.newProxyInstance(
                        CallA.class.getClassLoader(),
                        new Class[]{CallA.class},
                        handler);

        proxiedCallA.call();
        log.info("proxiedCallA is located at {}", proxiedCallA.getClass());
    }

    @Test
    void dynamicB() {
        CallB target = new CallBImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        CallB proxiedCallB =
                (CallB) Proxy.newProxyInstance(
                        CallB.class.getClassLoader(),
                        new Class[]{CallB.class},
                        handler);

        proxiedCallB.call();
        log.info("proxiedCallA is located at {}", proxiedCallB.getClass());
    }
}

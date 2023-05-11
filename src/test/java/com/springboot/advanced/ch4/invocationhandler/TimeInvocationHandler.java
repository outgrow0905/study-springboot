package com.springboot.advanced.ch4.invocationhandler;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
public class TimeInvocationHandler implements InvocationHandler {

    private final Object target;

    public TimeInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("TimeInvocationHandler invoke()");

        long startMs = System.currentTimeMillis();
        Object result = method.invoke(target, args);
        long endMs = System.currentTimeMillis();
        log.info("boxUsage: {}", endMs - startMs);
        return result;
    }
}

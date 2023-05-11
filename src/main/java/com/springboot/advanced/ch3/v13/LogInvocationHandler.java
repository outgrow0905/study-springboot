package com.springboot.advanced.ch3.v13;

import com.springboot.advanced.ch1.trace.LogTrace;
import com.springboot.advanced.ch2.v7.strategy.LogContext;
import com.springboot.advanced.ch2.v7.strategy.LogContextStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class LogInvocationHandler implements InvocationHandler {

    private final Object target;
    private final LogContext context;

    public LogInvocationHandler(Object target, LogTrace logTrace) {
        this.target = target;
        this.context = new LogContext(logTrace);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return context.execute(
                String.format("%s %s invoke()", method.getDeclaringClass().getSimpleName(), method.getName()),
                () -> {
                    try {
                        return method.invoke(target, args);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}

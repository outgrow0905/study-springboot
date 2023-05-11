package com.springboot.advanced.ch3.v15;

import com.springboot.advanced.ch1.trace.LogTrace;
import com.springboot.advanced.ch2.v7.strategy.LogContext;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class LogAdvice implements MethodInterceptor {

    private final LogContext context;

    public LogAdvice(LogTrace logTrace) {
        this.context = new LogContext(logTrace);
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        return context.execute(
                String.format("class %s; method %s; invoke()",
                        invocation.getMethod().getDeclaringClass().getSimpleName(), invocation.getMethod().getName()),
                () -> {
                    try {
                        return invocation.proceed();
                    } catch (Throwable e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}

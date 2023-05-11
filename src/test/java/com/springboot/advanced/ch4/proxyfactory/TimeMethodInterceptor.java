package com.springboot.advanced.ch4.proxyfactory;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.Interceptor;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class TimeMethodInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        long startMs = System.currentTimeMillis();
        Object result = invocation.proceed();
        log.info("boxUsage: {}", System.currentTimeMillis() - startMs);
        return result;
    }
}

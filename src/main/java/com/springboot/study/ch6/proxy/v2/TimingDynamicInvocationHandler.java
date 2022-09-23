package com.springboot.study.ch6.proxy.v2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimingDynamicInvocationHandler implements InvocationHandler {

    private Map<String, Method> methodMap;
    private Object target;

    public TimingDynamicInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = method.invoke(target, args);
        log.info("method {} boxUsage: {}", method.getName(), System.currentTimeMillis() - startTime);
        return result;
    }
}

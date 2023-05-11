package com.springboot.advanced.ch4.cglib;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

@Slf4j
@RequiredArgsConstructor
public class TimeMethodInterceptor implements MethodInterceptor {

    private final Object target;

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        long startMs = System.currentTimeMillis();

        Object result = methodProxy.invoke(target, args);
//        Object result = method.invoke(target, args);

        long endMs = System.currentTimeMillis();
        log.info("boxUsage: {}", endMs - startMs);

        return result;
    }
}

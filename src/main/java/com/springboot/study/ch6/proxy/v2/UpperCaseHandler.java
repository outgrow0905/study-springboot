package com.springboot.study.ch6.proxy.v2;

import com.springboot.study.ch6.proxy.v1.Hello;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class UpperCaseHandler implements InvocationHandler {

    Hello hello;

    public UpperCaseHandler(Hello hello) {
        this.hello = hello;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object ret = method.invoke(hello, args);
        if (ret instanceof String) {
            return ((String) ret).toUpperCase();
        }

        return ret;
    }
}

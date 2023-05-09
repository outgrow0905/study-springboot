package com.springboot.advanced.ch3.proxy;

import io.micrometer.core.instrument.util.StringUtils;

public class CacheProxy implements Subject{

    private Subject target;
    private String data;

    public CacheProxy(Subject subject) {
        this.target = subject;
    }

    @Override
    public String operation() {
        if (StringUtils.isEmpty(data)) {
            data = target.operation();
        }
        return data;
    }
}

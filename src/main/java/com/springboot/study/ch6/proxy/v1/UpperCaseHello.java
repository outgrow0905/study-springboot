package com.springboot.study.ch6.proxy.v1;

public class UpperCaseHello implements Hello {

    private Hello hello;

    public UpperCaseHello(Hello hello) {
        this.hello = hello;
    }

    @Override
    public String sayHello(String s) {
        return hello.sayHello(s).toUpperCase();
    }

    @Override
    public String byeHello(String s) {
        return hello.byeHello(s).toUpperCase();
    }

    @Override
    public Integer countHello(String s) {
        return hello.countHello(s);
    }
}

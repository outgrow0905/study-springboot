package com.springboot.study.ch6.proxy.v1;

public class TargetHello implements Hello {

    @Override
    public String sayHello(String s) {
        return "say " + s;
    }

    @Override
    public String byeHello(String s) {
        return "bye " + s;
    }

    @Override
    public Integer countHello(String s) {
        return s.length();
    }
}

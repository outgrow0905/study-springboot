package com.springboot.study.ch6.v10.proxyfactorybean;

public class MySay implements Say {

    @Override
    public String sayYeah(String str) {
        return "Yeah " + str;
    }

    @Override
    public String sayHi(String str) {
        return "Hi " + str;
    }
}

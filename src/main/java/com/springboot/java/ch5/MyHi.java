package com.springboot.java.ch5;

public class MyHi implements Hi{
    private String name;

    public MyHi(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}

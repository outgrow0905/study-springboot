package com.springboot.java.ch2;

import java.util.function.Function;

public class Plus10 implements Function<Integer, Integer> {
    @Override
    public Integer apply(Integer number) {
        return number + 10;
    }
}

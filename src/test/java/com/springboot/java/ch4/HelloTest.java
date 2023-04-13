package com.springboot.java.ch4;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class HelloTest {

    @Test
    void staticMethod() {
        Consumer<String> consumer = Hello::hello;
        consumer.accept("static");
    }

    @Test
    void instanceMethod() {
        Hello hello = new Hello();
        Consumer<String> consumer = hello::hi;
        consumer.accept("instance");
    }

    @Test
    void constructor() {
        // default constructor
        Supplier<Hello> supplier = Hello::new;

        // constructor with parameter
        Function<String, Hello> function = Hello::new;
    }

    @Test
    void difficult() {
        String[] names = {"banana", "apple", "mango"};
        Arrays.sort(names, String::compareToIgnoreCase);
    }
}
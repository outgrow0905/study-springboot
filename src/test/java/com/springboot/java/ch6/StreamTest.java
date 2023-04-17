package com.springboot.java.ch6;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

public class StreamTest {
    @Test
    void streamOperation() {
        List<String> fruits = List.of("apple", "banana", "mango", "blueberry");

        // intermediate operation
        // lazy
        Stream<String> lazyStream = fruits.stream().filter(fruit -> fruit.startsWith("b"));
        System.out.println(fruits);

        // terminate operation
        long count = lazyStream.count();
        System.out.println(count);
    }
}

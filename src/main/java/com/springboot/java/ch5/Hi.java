package com.springboot.java.ch5;

public interface Hi {
    default void hiUpperCase() {
        System.out.println("Hi " + getName().toUpperCase());
    }

    static void hello() {
        System.out.println("Hello");
    }

    String getName();
}

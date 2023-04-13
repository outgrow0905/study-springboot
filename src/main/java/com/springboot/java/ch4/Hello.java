package com.springboot.java.ch4;

public class Hello {
    String name;

    // default method
    public Hello() {}

    // construct with parameter
    public Hello(String name) {
        this.name = name;
    }

    // static method
    public static void hello(String str) {
        System.out.println("hello " + str);
    }

    // instance method
    public void hi(String str){
        System.out.println("hi " + str);
    }
}

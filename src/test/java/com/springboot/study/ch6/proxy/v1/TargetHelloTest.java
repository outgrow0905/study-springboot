package com.springboot.study.ch6.proxy.v1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TargetHelloTest {
    Hello hello = new TargetHello();

    @Test
    void say_hello() {
        assertEquals("say world!", hello.sayHello("world!"));
    }

    @Test
    void bye_hello() {
        assertEquals("bye world!", hello.byeHello("world!"));
    }

    @Test
    void count_hello() {
        assertEquals(5, hello.countHello("hello"));
    }
}
package com.springboot.study.ch6.proxy.v1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UpperCaseHelloTest {

    Hello proxyHello = new UpperCaseHello(new TargetHello());

    @Test
    void sayHello() {
        assertEquals("SAY WORLD!", proxyHello.sayHello("world!"));
    }

    @Test
    void bye_hello() {
        assertEquals("BYE WORLD!", proxyHello.byeHello("world!"));
    }

    @Test
    void count_hello() {
        assertEquals(5, proxyHello.countHello("hello"));
    }
}
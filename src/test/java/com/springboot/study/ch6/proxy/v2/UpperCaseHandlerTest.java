package com.springboot.study.ch6.proxy.v2;

import static org.junit.jupiter.api.Assertions.*;

import com.springboot.study.ch6.proxy.v1.Hello;
import com.springboot.study.ch6.proxy.v1.TargetHello;
import java.lang.reflect.Proxy;
import org.junit.jupiter.api.Test;

class UpperCaseHandlerTest {
    @Test
    void dynamic_handler() {
        Hello hello = (Hello)Proxy.newProxyInstance(
            getClass().getClassLoader(),
            new Class[]{Hello.class},
            new UpperCaseHandler(new TargetHello())
        );

        assertEquals("SAY WORLD!", hello.sayHello("world!"));
        assertEquals("BYE WORLD!", hello.byeHello("world!"));
        assertEquals(5, hello.countHello("hello"));
    }
}
package com.springboot.study.ch6.proxy.v1;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ReflectionTest {

    @Test
    void invoke_method() throws Exception {
        String spring = "Spring";

        Method stringLengthMethod = String.class.getMethod("length");
        assertEquals(6, spring.length());
        assertEquals(6, stringLengthMethod.invoke(spring));

        Method stringCharAtMethod = String.class.getMethod("charAt", int.class);
        assertEquals('S', spring.charAt(0));
        assertEquals('S', stringCharAtMethod.invoke(spring, 0));
    }
}

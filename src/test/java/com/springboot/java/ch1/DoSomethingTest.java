package com.springboot.java.ch1;

import lombok.Data;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoSomethingTest {
    @Test
    void anonymousInnerClass() {
        DoSomething doSomething = new DoSomething() {
            @Override
            public int doIt(int a) {
                return a + 1;
            }
        };

        assertEquals(2, doSomething.doIt(1));
        assertEquals(2, doSomething.doIt(1));
        assertEquals(2, doSomething.doIt(1));
    }

    @Test
    void anonymousInnerClassByLambda() {
        DoSomething doSomething = a -> a + 1;

        assertEquals(2, doSomething.doIt(1));
        assertEquals(2, doSomething.doIt(1));
        assertEquals(2, doSomething.doIt(1));
    }

    class Hello {
        int a;

        int plus(int number) {
            a = a + number;
            return a;
        }
    }

    @Test
    void violateFunctionalProgramming() {
        Hello hello = new Hello();

        DoSomething doSomething = hello::plus;

        assertEquals(1, doSomething.doIt(1));
        assertEquals(2, doSomething.doIt(1));
        assertEquals(3, doSomething.doIt(1));
    }
}
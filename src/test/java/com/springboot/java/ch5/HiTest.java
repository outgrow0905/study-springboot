package com.springboot.java.ch5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HiTest {
    @Test
    void defaultInterfaceError() {
        Hi hi1 = new MyHi("june");
        hi1.hiUpperCase();

        // NPE
        Hi hi2 = new MyHi(null);
        hi2.hiUpperCase();
    }

    @Test
    void interfaceStaticMethod() {
        Hi.hello();
    }
}
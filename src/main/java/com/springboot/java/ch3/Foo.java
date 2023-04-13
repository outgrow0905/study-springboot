package com.springboot.java.ch3;

import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;

public class Foo {
    void run() {
        int myNumber = 10;

        // Local class
        class Bar {
            void run() {
                int myNumber = 11;
                System.out.println(myNumber); // 11
            }
        }

        // anonymous class
        IntConsumer consumer = new IntConsumer() {
            @Override
            public void accept(int value) {
                int myNumber = 12;
                System.out.println(myNumber); // 12
            }
        };

        // Lambda
        IntConsumer functionByLambda = i -> {
//            int myNumber = 13;
            System.out.println(myNumber);
        };
    }
}

package com.springboot.java.ch3;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class Bar {
    void run() {
        int myNumber = 10;

        // Local class
        class Foo {
            void run() {
//                myNumber++;
                System.out.println(myNumber); // 11
            }
        }

        // anonymous class
        IntConsumer consumer = new IntConsumer() {
            @Override
            public void accept(int value) {
//                myNumber++;
                System.out.println(myNumber); // 12
            }
        };

        // Lambda
        IntConsumer functionByLambda = i -> {
//            myNumber++;
            System.out.println(myNumber);
        };
    }
}

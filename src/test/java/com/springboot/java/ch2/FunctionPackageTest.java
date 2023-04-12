package com.springboot.java.ch2;

import org.junit.jupiter.api.Test;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.function.*;

import static org.junit.jupiter.api.Assertions.*;

class FunctionPackageTest {

    @Test
    void plus10() {
        Plus10 plus10 = new Plus10();
        assertEquals(11, plus10.apply(1));
    }

    @Test
    void plus10ByAnonymousInnerClass() {
        Function<Integer, Integer> plus10 = integer -> integer + 10;
        assertEquals(11, plus10.apply(1));
    }

    @Test
    void functionDefaultMethod() {
        Function<Integer, Integer> plus10 = integer -> integer + 10;
        Function<Integer, Integer> minus5 = integer -> integer - 5;
        Function<Integer, Integer> multiply2 = integer -> integer * 2;

        Function<Integer, Integer> plus10AndMinus5AndMultiply2Function = plus10.andThen(minus5.andThen(multiply2));
        Function<Integer, Integer> multiply2AndMinus5AndPlus10Function = plus10.compose(minus5.compose(multiply2));

        assertEquals(30, plus10AndMinus5AndMultiply2Function.apply(10));
        assertEquals(25, multiply2AndMinus5AndPlus10Function.apply(10));
    }

    @Test
    void biFunction() {
        BiFunction<Integer, Integer, Integer> sumAndPlus10 = (a, b) -> a + b + 10;
        assertEquals(20, sumAndPlus10.apply(5, 5));
    }


    @Test
    void consumer() {
        Consumer<String> helloConsumer = str -> System.out.println("hello " + str);
        helloConsumer.accept("lambda");

        Consumer<String> byeConsumer = str -> System.out.println("bye " + str);
        Consumer<String> helloAndByeConsumer = helloConsumer.andThen(byeConsumer);
        helloAndByeConsumer.accept("lambda");
    }

    @Test
    void supplier() {
        Supplier<Integer> return10 = () -> 10;
        assertEquals(10, return10.get());
    }

    @Test
    void predicate() {
        Predicate<Integer> isEven = i -> i % 2 == 0;
        assertTrue(isEven.test(2));
        assertFalse(isEven.test(1));
    }

    @Test
    void intPredicate() {
        IntPredicate isEven = i -> i % 2 == 0;
        assertTrue(isEven.test(2));
        assertFalse(isEven.test(1));
    }
}
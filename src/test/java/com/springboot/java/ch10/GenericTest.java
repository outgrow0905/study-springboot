package com.springboot.java.ch10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.Box;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GenericTest {
    @Test
    void plainBox() {
        PlainBox box = new PlainBox();
        box.set("hello");
        assertThrows(ClassCastException.class, () -> {
            Integer errorInteger = (Integer) box.get();
        });
    }

    @Test
    void genericBox() {
        GenericBox<String> box = new GenericBox<>();
        box.set("hello");
//        Integer hello = box.get(); // compile error
    }

    @Test
    void typeCast() {
        // 타입캐스트 필요
        PlainBox plainBox = new PlainBox();
        plainBox.set("hello");
        String hello = (String)plainBox.get();

        // 타입캐스트 불필요
        GenericBox<String> genericBox = new GenericBox<>();
        genericBox.set("world");
        String world = genericBox.get();
    }

    @Test
    void boundedType() {
        List<? extends Number> numbers = List.of(1, 2, 3);
        numbers.forEach(number -> System.out.println(number.intValue()));
    }

    @Test
    void inheritance1() {
        GenericBox<Number> numberBox = new GenericBox<>();
        GenericBox<Integer> integerBox = new GenericBox<>();
//        numberBox = integerBox; // compile error
    }

    @Test
    void inheritance2() {
        Collection<String> stringCollection = new ArrayList<>();
        List<String> stringList = new ArrayList<>();
        stringCollection = stringList;
    }

    @Test
    void inheritance3() {
        MyList<String, Integer> myList1 = null;
        MyList<String, Double> myList2 = null;
        List<String> list = new ArrayList<>();

        list = myList1;
        list = myList2;
    }
}
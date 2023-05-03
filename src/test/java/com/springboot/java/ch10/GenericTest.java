package com.springboot.java.ch10;

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
}
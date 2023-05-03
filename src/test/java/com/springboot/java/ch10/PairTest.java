package com.springboot.java.ch10;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PairTest {
    @Test
    void compare() {
        PairUtil.compare(new Pair<>("p1", 1), new Pair<>(1, 2));
//        PairUtil.<String, Integer>compare(new Pair<>("p1", 1), new Pair<>(1, 2));
    }
}
package com.springboot.java.ch7;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class OnlineClassTest {
    List<OnlineClass> springClasses;

    @BeforeEach
    void setUp() {
        springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring core", true));
        springClasses.add(new OnlineClass(3, "spring jpa", false));
        springClasses.add(new OnlineClass(4, "spring batch", false));
        springClasses.add(new OnlineClass(5, "rest api", false));
    }

    @Test
    void optional1() {
        // NPE
//        System.out.println("duration:" + springClasses.get(0).getProgress().getDuration());

        // NPE fixed
        Progress progress = springClasses.get(0).getProgress();
        if (null != progress) {
            System.out.println("duration: " + progress.getDuration());
        }
    }

    @Test
    void optional2() {
        springClasses.get(0).getOptionalProgress().ifPresent(System.out::println);
    }
}
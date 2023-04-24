package com.springboot.java.ch7;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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

    @Test
    void ifPresent() {
        // findAny() returns Optional type
        Optional<OnlineClass> springClass = springClasses.stream()
                .filter(onlineClass -> onlineClass.getTitle().startsWith("spring"))
                .findAny();

        // don't use like below 1
        if (springClass.isPresent()) {
            OnlineClass onlineClass = springClass.get();
        }

        // don't use like below 2
//        OnlineClass onlineClass = springClass.get();


        // ifPresent
        springClass.ifPresent(oc -> {
            // logics..
        });
    }

    @Test
    void orElse() {
        // findAny() returns Optional type
        Optional<OnlineClass> springClass = springClasses.stream()
                .filter(onlineClass -> onlineClass.getTitle().startsWith("spring"))
                .findAny();

        // orElse
        OnlineClass onlineClass = springClass.orElse(createNullOnlineClass());
    }

    private OnlineClass createNullOnlineClass() {
        System.out.println("create OnlineClass null instance");
        return new OnlineClass(999, "no title", false);
    }

    @Test
    void orElseGet() {
        // findAny() returns Optional type
        Optional<OnlineClass> springClass = springClasses.stream()
                .filter(onlineClass -> onlineClass.getTitle().startsWith("spring"))
                .findAny();

        // orElseGet
        OnlineClass onlineClass = springClass.orElseGet(this::createNullOnlineClass);
    }

    @Test
    void orElseThrow() {
        // findAny() returns Optional type
        Optional<OnlineClass> springClass = springClasses.stream()
                .filter(onlineClass -> onlineClass.getTitle().startsWith("notExists"))
                .findAny();

        // orElseThrow
        try {
            OnlineClass onlineClass = springClass.orElseThrow();
        } catch (NoSuchElementException e) {
            System.out.println("NoSuchElementException occurs");
        }
    }

    @Test
    void filter() {
        // findAny() returns Optional type
        Optional<OnlineClass> springClass = springClasses.stream()
                .filter(onlineClass -> onlineClass.getTitle().startsWith("notExists"))
                .findAny();

        // filter
        Optional<OnlineClass> onlineClass = springClass.filter(oc -> oc.getId() > 99999);
        System.out.println(onlineClass.isEmpty());
    }

    @Test
    void map() {
        // findAny() returns Optional type
        Optional<OnlineClass> springClass = springClasses.stream()
                .filter(onlineClass -> onlineClass.getTitle().startsWith("spring"))
                .findAny();

        // map
        Optional<Integer> optionalId = springClass.map(OnlineClass::getId);
    }

    @Test
    void flatMap() {
        // findAny() returns Optional type
        Optional<OnlineClass> springClass = springClasses.stream()
                .filter(onlineClass -> onlineClass.getTitle().startsWith("spring"))
                .findAny();

        // map
        Optional<Optional<Progress>> progressByMap = springClass.map(OnlineClass::getOptionalProgress);

        // flatMap
        Optional<Progress> progressByFlatMap = springClass.flatMap(OnlineClass::getOptionalProgress);
    }
}

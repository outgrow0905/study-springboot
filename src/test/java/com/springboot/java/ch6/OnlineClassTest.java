package com.springboot.java.ch6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class OnlineClassTest {

    List<OnlineClass> springClasses;
    List<OnlineClass> javaClasses;

    List<List<OnlineClass>> allClasses;

    @BeforeEach
    void setUp() {
        springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring core", true));
        springClasses.add(new OnlineClass(3, "spring jpa", false));
        springClasses.add(new OnlineClass(4, "spring batch", false));
        springClasses.add(new OnlineClass(5, "rest api", false));

        javaClasses = new ArrayList<>();
        javaClasses.add(new OnlineClass(6, "java junit", true));
        javaClasses.add(new OnlineClass(7, "java 11", true));
        javaClasses.add(new OnlineClass(8, "java design pattern", false));

        allClasses = new ArrayList<>();
        allClasses.addAll(List.of(springClasses, javaClasses));
    }

    @Test
    void startWithSpring() {
        List<OnlineClass> classes =
                springClasses.stream()
                        .filter(onlineClass -> onlineClass.getTitle().startsWith("spring"))
                        .collect(Collectors.toList());
        classes.forEach(System.out::println);
    }

    @Test
    void notClosedClass() {
        List<OnlineClass> classes1 =
                springClasses.stream()
                        .filter(onlineClass -> !onlineClass.isClosed())
                        .collect(Collectors.toList());
        classes1.forEach(System.out::println);

        System.out.println("=====");

        List<OnlineClass> classes2 =
                springClasses.stream()
                        .filter(Predicate.not(OnlineClass::isClosed))
                        .collect(Collectors.toList());
        classes2.forEach(System.out::println);
    }

    @Test
    void classNameList(){
        List<String> classNameList =
                springClasses.stream()
                        .map(OnlineClass::getTitle)
                        .collect(Collectors.toList());
        classNameList.forEach(System.out::println);
    }

    @Test
    void getIdListFromAllClasses() {
        List<Integer> idList = allClasses.stream()
                .flatMap(Collection::stream)
                .map(OnlineClass::getId)
                .collect(Collectors.toList());
        idList.forEach(System.out::println);
    }

    @Test
    void iterate() {
        Stream.iterate(10, i -> i + 1)
                .skip(10)
                .limit(10)
                .forEach(System.out::println);
    }
}
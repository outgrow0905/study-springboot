package com.springboot.java.ch6;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StreamTest {
    @Test
    void streamOperation() {
        List<String> fruits = List.of("apple", "banana", "mango", "blueberry");

        // intermediate operation
        // lazy
        Stream<String> lazyStream = fruits.stream().filter(fruit -> fruit.startsWith("b"));
        System.out.println(fruits);

        // terminate operation
        long count = lazyStream.count();
        System.out.println(count);
    }

    @Test
    void streamReduce1() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        int result = numbers
                .stream()
                .reduce(0, (subtotal, element) -> subtotal + element);
        assertEquals(21, result);
    }

    @Test
    void streamReduce2() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        int result = numbers.parallelStream().reduce(0, (a, b) -> a + b, Integer::sum);
        assertEquals(21, result);
    }

    @Test
    void streamReduce3() {
        List<User> users = List.of(new User("John", 30), new User("Julie", 35));
        int sumAge =
                users.stream().reduce(
                        0,
                        (partialAge, user) -> partialAge + user.getAge(), Integer::sum);
    }

    @Test
    void streamReduce4() {
        User john = new User("John", 30);
        john.getRating().add(new Review(5, ""));
        john.getRating().add(new Review(3, "not bad"));

        User julie = new User("Julie", 35);
        julie.getRating().add(new Review(4, "great!"));
        julie.getRating().add(new Review(2, "terrible experience"));
        julie.getRating().add(new Review(4, ""));

        List<User> users = List.of(john, julie);
        Rating rating = users.stream()
                .map(User::getRating)
                .reduce(
                        new Rating(),
                        Rating::average);
        System.out.println("average rating: " + rating.points);
    }

    @Test
    void streamOperation2() {
        List<String> fruits = List.of("apple", "banana", "mango", "blueberry");

        List<String> upperCasedFruits = fruits.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(fruits); // don't change source
        System.out.println(upperCasedFruits);
    }
}

package com.springboot.java.ch6;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class ArticleTest {

    @Test
    void spliterator() {
        List<Article> articles = Stream.generate(() -> new Article("Java"))
                .limit(35000)
                .collect(Collectors.toList());

        Spliterator<Article> spliterator1 = articles.spliterator();
        Spliterator<Article> spliterator2 = spliterator1.trySplit();

        log.info("size: {}", spliterator1.estimateSize());
        log.info("size: {}", spliterator2.estimateSize());

        assertTrue(new Task(spliterator1).call().contains(String.valueOf(articles.size() / 2)));
        assertTrue(new Task(spliterator2).call().contains(String.valueOf(articles.size() / 2)));

        log.info("size: {}", spliterator1.estimateSize());
        log.info("size: {}", spliterator2.estimateSize());

        log.info("characteristics: {}", spliterator1.characteristics());
        log.info("characteristics: {}", spliterator2.characteristics());
    }
}
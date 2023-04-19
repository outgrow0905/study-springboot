package com.springboot.java.ch6;

import java.util.Spliterator;

public class Task {
    Spliterator<Article> spliterator;

    public Task(Spliterator<Article> spliterator) {
        this.spliterator = spliterator;
    }

    public String call() {
        int current = 0;
        while (spliterator.tryAdvance(
                article ->
                        article.setName(article.getName().concat("- published by Baeldung")))) {
            current++;
        }

        return Thread.currentThread().getName() + ":" + current;
    }
}

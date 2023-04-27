package com.springboot.java.ch9;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

public class CompletableFutureTest {
    @Test
    void callableProblem() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Callable<String> callable = () -> "hello";
        Future<String> future = executorService.submit(callable);

        // tasks which have nothing to do with future must be written here as much as possible

        String hello = future.get();

        // you can write anything as non-blocking tasks are finished
    }

    @Test
    void completableFuture() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture1 = new CompletableFuture<>();
        completableFuture1.complete("hello1");
        System.out.println(completableFuture1.get());

        CompletableFuture<String> completableFuture2 = CompletableFuture.completedFuture("hello2");
        System.out.println(completableFuture2.get());
    }

    @Test
    void runAsync() {
        System.out.println("start");
        CompletableFuture.runAsync(() -> System.out.println("hello"));
        System.out.println("end");
    }

    @Test
    void supplyAsync() throws ExecutionException, InterruptedException {
        System.out.println("start");
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("hello");
            return "world";
        });
        System.out.println(future.get());
        System.out.println("end");
    }

    @Test
    void thenApply() throws ExecutionException, InterruptedException {
        System.out.println("start");
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("hello " + Thread.currentThread().getName());
            return "Hello";
        }).thenApply(s -> {
            System.out.println("world " + Thread.currentThread().getName());
            return s.toUpperCase();
        });
        System.out.println("end");
        System.out.println(future.get());
    }

}

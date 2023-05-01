package com.springboot.java.ch9;

import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.stream.Collectors;

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


    @Test
    void thenCompose() throws ExecutionException, InterruptedException {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("hello thread: " + Thread.currentThread().getName());
            return "hello";
        });

        CompletableFuture<String> future = hello.thenCompose(s -> world(s));
        System.out.println(future.get());
    }

    private CompletableFuture<String> world(String str) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("world thread: " + Thread.currentThread().getName());
            return str + " world";
        });
    }

    @Test
    void thenCombine() throws ExecutionException, InterruptedException {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("hello thread: " + Thread.currentThread().getName());
            return "hello";
        });
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
            System.out.println("world thread: " + Thread.currentThread().getName());
            return "world";
        });

        CompletableFuture<String> future = hello.thenCombine(world, (s1, s2) -> s1 + " " + s2);
        System.out.println(future.get());
    }

    @Test
    void allOf1() {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("hello thread: " + Thread.currentThread().getName());
            return "hello";
        });
        CompletableFuture<String> java = CompletableFuture.supplyAsync(() -> {
            System.out.println("java thread: " + Thread.currentThread().getName());
            return "java";
        });
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
            System.out.println("world thread: " + Thread.currentThread().getName());
            return "world";
        });

        CompletableFuture<Void> futures = CompletableFuture.allOf(hello, java, world);
    }

    @Test
    void allOf2() throws ExecutionException, InterruptedException {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("hello thread: " + Thread.currentThread().getName());
            return "hello";
        });
        CompletableFuture<String> java = CompletableFuture.supplyAsync(() -> {
            System.out.println("java thread: " + Thread.currentThread().getName());
            return "java";
        });
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
            System.out.println("world thread: " + Thread.currentThread().getName());
            return "world";
        });

        CompletableFuture<String>[] futures = new CompletableFuture[]{hello, java, world};
        CompletableFuture<List<String>> results = CompletableFuture.allOf(futures)
                .thenApply(v -> // v는 아무런 의미가 없다.
                        // thenApply는 allOf 리스트의 모든 작업이 끝났음을 의미한다.
                        // 미리 지정해둔 futures를 순회하면서 join을 통해 결과값들을 리스트로 다시 저장한다.
                        Arrays.stream(futures)
                                .map(CompletableFuture::join)
                                .collect(Collectors.toList()));

        results.get().forEach(System.out::println);
    }

    @Test
    void exceptionally() throws ExecutionException, InterruptedException {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            boolean logic = true;
            if (logic) {
                throw new RuntimeException();
            }
            System.out.println("Thread: " + Thread.currentThread().getName());
            return "hello";
        }).exceptionally(throwable -> {
            System.out.println(throwable);
            return "error";
        });

        System.out.println(hello.get());
    }

    @Test
    void handle() throws ExecutionException, InterruptedException {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            boolean logic = true;
            if (!logic) {
                throw new RuntimeException();
            }
            System.out.println("Thread: " + Thread.currentThread().getName());
            return "hello";
        }).handle((s, throwable) -> {
            if (Objects.equals(s, "hello")) {
                return "world";
            }
            return s;
        });

        System.out.println(hello.get());
    }
}

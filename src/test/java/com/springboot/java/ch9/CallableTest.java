package com.springboot.java.ch9;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class CallableTest {

    @Test
    void runnable() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        System.out.println("start");
        executorService.submit(showMessageByRunnable("hello"));
        executorService.submit(showMessageByRunnable("world"));
        executorService.submit(showMessageByRunnable("my"));
        executorService.submit(showMessageByRunnable("name"));
        executorService.submit(showMessageByRunnable("is"));
        System.out.println("end");
    }

    @Test
    void callableNotUsingFuture() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        System.out.println("start");
        Future<String> hello = executorService.submit(showMessageByCallable("hello"));
        System.out.println("hello isDone(): " + hello.isDone());
        Future<String> world = executorService.submit(showMessageByCallable("world"));
        System.out.println("world isDone(): " + world.isDone());
        Future<String> my = executorService.submit(showMessageByCallable("my"));
        System.out.println("my isDone(): " + my.isDone());
        Future<String> name = executorService.submit(showMessageByCallable("name"));
        System.out.println("name isDone(): " + name.isDone());
        Future<String> is = executorService.submit(showMessageByCallable("is"));
        System.out.println("is isDone(): " + is.isDone());
        System.out.println("end");
    }


    @Test
    void callableUsingFuture() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        System.out.println("start");
        Future<String> hello = executorService.submit(showMessageByCallable("hello"));
        System.out.println("hello isDone(): " + hello.isDone());
        hello.get();
        System.out.println("hello isDone(): " + hello.isDone());
        Future<String> world = executorService.submit(showMessageByCallable("world"));
        System.out.println("world isDone(): " + world.isDone());
        world.get();
        System.out.println("world isDone(): " + world.isDone());
        Future<String> my = executorService.submit(showMessageByCallable("my"));
        System.out.println("my isDone(): " + my.isDone());
        my.get();
        System.out.println("my isDone(): " + my.isDone());
        Future<String> name = executorService.submit(showMessageByCallable("name"));
        System.out.println("name isDone(): " + name.isDone());
        name.get();
        System.out.println("name isDone(): " + name.isDone());
        Future<String> is = executorService.submit(showMessageByCallable("is"));
        System.out.println("is isDone(): " + is.isDone());
        is.get();
        System.out.println("is isDone(): " + is.isDone());
        System.out.println("end");
    }

     @Test
     void invokeAll() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Callable<String> callable1 = () -> {
            Thread.sleep(1000);
            return "callable1";
        };
        Callable<String> callable2 = () -> {
             Thread.sleep(2000);
             return "callable2";
        };
        Callable<String> callable3 = () -> {
             Thread.sleep(3000);
             return "callable3";
        };

         List<Future<String>> futures = executorService.invokeAll(Arrays.asList(callable1, callable2, callable3));
         for (Future<String> future : futures) {
             System.out.println(future.get());
         }
     }

    @Test
    void invokeAny() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Callable<String> callable1 = () -> {
            Thread.sleep(3000);
            return "callable1";
        };
        Callable<String> callable2 = () -> {
            Thread.sleep(2000);
            return "callable2";
        };
        Callable<String> callable3 = () -> {
            Thread.sleep(1000);
            return "callable3";
        };

        String any = executorService.invokeAny(Arrays.asList(callable1, callable2, callable3));
        System.out.println(any);
    }

    private Callable<String> showMessageByCallable(String message) {
        return () -> {
            System.out.println(message + " Thread: " + Thread.currentThread().getName());
            return "hello " + message;
        };
    }

    private Runnable showMessageByRunnable(String message) {
        return () -> {
            System.out.println(message + " Thread: " + Thread.currentThread().getName());
        };
    }
}

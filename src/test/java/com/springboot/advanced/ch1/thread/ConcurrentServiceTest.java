package com.springboot.advanced.ch1.thread;

import com.springboot.advanced.ch1.thread.code.ConcurrentService;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ConcurrentServiceTest {
    private final ConcurrentService concurrentService = new ConcurrentService();

    @Test
    void concurrent() throws ExecutionException, InterruptedException {
        CompletableFuture<String> result1 =
            CompletableFuture.supplyAsync(() -> concurrentService.setNameAndGet("hello"));
        CompletableFuture<String> result2 =
            CompletableFuture.supplyAsync(() -> concurrentService.setNameAndGet("world"));

        result1.get();
        result2.get();
    }
}

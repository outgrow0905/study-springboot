package com.springboot.java.ch9.v1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class MyBank {
    private int totalMoney;

    public void collectBankMoney() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> tossMoney =
                CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("call toss..");
                    return 10000;
                }).thenApply(money -> totalMoney += money);
        CompletableFuture<Integer> kakaoMoney =
                CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("call kakao..");
                    return 20000;
                }).thenApply(money -> totalMoney += money);
        CompletableFuture<Integer> naverMoney =
                CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("call naver..");
                    return 40000;
                }).thenApply(money -> totalMoney += money);

        System.out.println("after toss " + tossMoney.get());
        System.out.println("after kakao " + kakaoMoney.get());
        System.out.println("after naver " + naverMoney.get());


    }

    public int getTotalMoney() {
        return totalMoney;
    }
}

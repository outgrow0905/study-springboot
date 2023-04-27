package com.springboot.java.ch9.v2;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class MyBank {
    private int totalMoney;

    public void collectBankMoney() throws ExecutionException, InterruptedException {
//        CompletableFuture.allOf(getTossMoney(), getKakaoMoney(), getNaverMoney());
//
//        System.out.println("after toss " + tossMoney.get());
//        System.out.println("after kakao " + kakaoMoney.get());
//        System.out.println("after naver " + naverMoney.get());


    }

    private CompletableFuture<Integer> getNaverMoney() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("call naver..");
            return 40000;
        });
    }

    private CompletableFuture<Integer> getTossMoney() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("call toss..");
            return 10000;
        }).thenApply(money -> totalMoney += money);
    }

    private CompletableFuture<Integer> getKakaoMoney() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("call kakao..");
            return 20000;
        }).thenApply(money -> totalMoney += money);
    }

    public int getTotalMoney() {
        return totalMoney;
    }
}

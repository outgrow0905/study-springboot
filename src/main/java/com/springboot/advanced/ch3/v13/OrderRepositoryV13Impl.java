package com.springboot.advanced.ch3.v13;

public class OrderRepositoryV13Impl implements OrderRepositoryV13 {
    public void save(String itemId) {
        if (itemId.equals("ex")) {
            throw new IllegalStateException("IllegalStateException occur");
        }
        sleep(1000);
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

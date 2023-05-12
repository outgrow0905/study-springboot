package com.springboot.advanced.ch3.v17;

import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryV17 {
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

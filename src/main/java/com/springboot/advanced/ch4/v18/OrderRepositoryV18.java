package com.springboot.advanced.ch4.v18;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryV18 {
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

package com.springboot.advanced.ch3.v17;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component // why @Repository is auto proxied..?
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

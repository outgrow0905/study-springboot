package com.springboot.advanced.ch1.thread.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConcurrentService {
    private String nameStore;

    public String setNameAndGet(String name) {
        log.debug("Thread: {}, name: {}", Thread.currentThread().getName(), name);
        this.nameStore = name;
        sleep(1000);
        log.debug("Thread: {}, nameStore: {}", Thread.currentThread().getName(), nameStore);

        return nameStore;
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

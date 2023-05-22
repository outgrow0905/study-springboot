package com.springboot.aop.ch3.v1;

import org.springframework.stereotype.Repository;

@Repository
public class ExamRepositoryV1 {
    private static int count = 0;

    public String save(String itemId) {
        count++;
        if (count % 5 == 0) {
            throw new IllegalStateException("IllegalStateException occur");
        }

        return "ok";
    }
}

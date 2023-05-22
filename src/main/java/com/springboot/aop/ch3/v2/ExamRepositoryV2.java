package com.springboot.aop.ch3.v2;

import com.springboot.aop.ch3.annotation.Trace;
import org.springframework.stereotype.Repository;

@Repository
public class ExamRepositoryV2 {
    private static int count = 0;

    @Trace
    public String save(String itemId) {
        count++;
        if (count % 5 == 0) {
            throw new IllegalStateException("IllegalStateException occur");
        }

        return "ok";
    }
}

package com.springboot.aop.ch1.v3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class BillingRepositoryV3 {
    public String save(String itemId) {
        log.info("{}, {}",
                new Object(){}.getClass().getName(),
                new Object(){}.getClass().getEnclosingMethod().getName());
        if (itemId.equals("ex")) {
            throw new IllegalStateException("IllegalStateException occur");
        }
        return "ok";
    }
}

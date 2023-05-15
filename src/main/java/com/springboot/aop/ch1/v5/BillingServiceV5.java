package com.springboot.aop.ch1.v5;

import com.springboot.aop.ch1.v4.BillingRepositoryV4;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BillingServiceV5 {
    private final BillingRepositoryV5 billingRepository;

    public BillingServiceV5(BillingRepositoryV5 billingRepository) {
        this.billingRepository = billingRepository;
    }

    public void orderItem(String itemId) {
        log.info("{}, {}",
                new Object(){}.getClass().getName(),
                new Object(){}.getClass().getEnclosingMethod().getName());
        billingRepository.save(itemId);
    }
}

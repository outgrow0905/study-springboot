package com.springboot.aop.ch1.v6;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BillingServiceV6 {
    private final BillingRepositoryV6 billingRepository;

    public BillingServiceV6(BillingRepositoryV6 billingRepository) {
        this.billingRepository = billingRepository;
    }

    public void orderItem(String itemId) {
        log.info("{}, {}",
                new Object(){}.getClass().getName(),
                new Object(){}.getClass().getEnclosingMethod().getName());
        billingRepository.save(itemId);
    }
}

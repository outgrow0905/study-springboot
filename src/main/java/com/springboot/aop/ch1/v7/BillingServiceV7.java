package com.springboot.aop.ch1.v7;

import com.springboot.aop.ch1.v6.BillingRepositoryV6;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BillingServiceV7 {
    private final BillingRepositoryV7 billingRepository;

    public BillingServiceV7(BillingRepositoryV7 billingRepository) {
        this.billingRepository = billingRepository;
    }

    public void orderItem(String itemId) {
        log.info("{}, {}",
                new Object(){}.getClass().getName(),
                new Object(){}.getClass().getEnclosingMethod().getName());
        billingRepository.save(itemId);
    }
}

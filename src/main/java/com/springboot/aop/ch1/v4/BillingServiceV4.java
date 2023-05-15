package com.springboot.aop.ch1.v4;

import com.springboot.aop.ch1.v3.BillingRepositoryV3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BillingServiceV4 {
    private final BillingRepositoryV4 billingRepository;

    public BillingServiceV4(BillingRepositoryV4 billingRepository) {
        this.billingRepository = billingRepository;
    }

    public void orderItem(String itemId) {
        log.info("{}, {}",
                new Object(){}.getClass().getName(),
                new Object(){}.getClass().getEnclosingMethod().getName());
        billingRepository.save(itemId);
    }
}

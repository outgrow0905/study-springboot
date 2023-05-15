package com.springboot.aop.ch1.v3;

import com.springboot.aop.ch1.v2.BillingRepositoryV2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BillingServiceV3 {
    private final BillingRepositoryV3 billingRepository;

    public BillingServiceV3(BillingRepositoryV3 billingRepository) {
        this.billingRepository = billingRepository;
    }

    public void orderItem(String itemId) {
        log.info("{}, {}",
                new Object(){}.getClass().getName(),
                new Object(){}.getClass().getEnclosingMethod().getName());
        billingRepository.save(itemId);
    }
}

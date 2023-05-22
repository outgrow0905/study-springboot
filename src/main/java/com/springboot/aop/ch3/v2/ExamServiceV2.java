package com.springboot.aop.ch3.v2;

import com.springboot.aop.ch3.annotation.Retry;
import com.springboot.aop.ch3.annotation.Trace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamServiceV2 {
    private final ExamRepositoryV2 examRepository;

    @Trace
    @Retry(4)
    public void request(String itemId) {
        examRepository.save(itemId);
    }
}

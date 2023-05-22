package com.springboot.aop.ch3.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamServiceV1 {
    private final ExamRepositoryV1 examRepository;

    public void request(String itemId) {
        examRepository.save(itemId);
    }
}

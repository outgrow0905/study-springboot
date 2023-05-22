package com.springboot.aop.ch3.v1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExamServiceTest {
    @Autowired
    private ExamServiceV1 examService;

    @Test
    void request5Times() {
        examService.request("itemA");
        examService.request("itemA");
        examService.request("itemA");
        examService.request("itemA");
        assertThrows(IllegalStateException.class, () -> examService.request("itemA"));
    }
}
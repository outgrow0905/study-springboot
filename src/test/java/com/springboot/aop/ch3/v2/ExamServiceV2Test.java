package com.springboot.aop.ch3.v2;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExamServiceV2Test {
    @Autowired
    private ExamServiceV2 examService;

    @Test
    void request5Times() {
        examService.request("item1");
        examService.request("item2");
        examService.request("item3");
        examService.request("item4");
        examService.request("item5"); // retry success
    }
}
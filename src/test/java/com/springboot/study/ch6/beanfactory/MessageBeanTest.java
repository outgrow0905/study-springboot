package com.springboot.study.ch6.beanfactory;

import static org.junit.jupiter.api.Assertions.*;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class MessageBeanTest {
    @Autowired
    private Message myMessage1;

    @Autowired
    private Message myMessage2;

    @Test
    void bean_with_private_constructor() {
        assertEquals("hello myMessage!", myMessage1.getText());
    }

    @Test
    void bean_from_bean_factory() {
        assertEquals("message bean by messageFactoryBean", myMessage2.getText());
    }
}
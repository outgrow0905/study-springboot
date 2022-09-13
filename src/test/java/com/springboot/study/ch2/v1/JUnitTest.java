package com.springboot.study.ch2.v1;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringBootTest
public class JUnitTest {
    private static Set<JUnitTest> jUnitTestSet = new HashSet<>();

    @Autowired
    private ApplicationContext context1;
    private static ApplicationContext context2;

    @Test
    void test1() {
        // 각 @Test가 별도의 JUnitTest 클래스를 생성하는지 테스트
        assertThat(jUnitTestSet).doesNotContain(this);
        jUnitTestSet.add(this);

        // 각 @Test가 ApplicationContext를 공유하는지 테스트
        assertTrue(context2 == null || context1 == context2);
        context2 = context1;
    }

    @Test
    void test2() {
        assertThat(jUnitTestSet).doesNotContain(this);
        jUnitTestSet.add(this);

        assertTrue(context2 == null || context1 == context2);
        context2 = context1;
    }

    @Test
    void test3() {
        assertThat(jUnitTestSet).doesNotContain(this);
        jUnitTestSet.add(this);

        assertTrue(context2 == null || context1 == context2);
        context2 = context1;
    }

}

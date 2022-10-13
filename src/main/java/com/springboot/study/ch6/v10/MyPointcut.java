package com.springboot.study.ch6.v10;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;

@Slf4j
@Configuration
public class MyPointcut {

    @Bean
    public NameMatchMethodPointcut myNameMatchPointcut() {
        NameMatchMethodPointcut nameMatchMethodPointcut = new NameMatchMethodPointcut();
        nameMatchMethodPointcut.setMappedName("gradeUsers");
//        nameMatchMethodPointcut.setClassFilter((ClassFilter) clazz -> {
//            log.info(clazz.getSimpleName());
//            log.info("match: {}", PatternMatchUtils.simpleMatch("*UserServiceV5", clazz.getSimpleName()));
//            return PatternMatchUtils.simpleMatch("*UserServiceV5", clazz.getSimpleName());
//        });
        return nameMatchMethodPointcut;
    }
}

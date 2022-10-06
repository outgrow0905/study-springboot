package com.springboot.study.ch6.v11;

import com.springboot.study.ch6.v8.UserServiceV5;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;

@Slf4j
@Component
public class MyPointcutV2 {

    @Autowired
    private UserServiceV5 userServiceV5;

    @Bean
    public UserServiceV5 userServiceV8() {
        return userServiceV5;
    }

    @Bean
    public NameMatchMethodPointcut myNameMatchPointcutV2() {
        NameMatchMethodPointcut nameMatchMethodPointcut = new NameMatchMethodPointcut();
        nameMatchMethodPointcut.setMappedName("gradeUsers");
        nameMatchMethodPointcut.setClassFilter((ClassFilter) clazz -> {
            return PatternMatchUtils.simpleMatch(clazz.getSimpleName(), "userServiceV8");
        });

        return nameMatchMethodPointcut;
    }
}

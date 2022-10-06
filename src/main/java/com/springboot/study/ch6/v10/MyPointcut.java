package com.springboot.study.ch6.v10;

import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyPointcut {

    @Bean
    public NameMatchMethodPointcut myNameMatchPointcut() {
        NameMatchMethodPointcut nameMatchMethodPointcut = new NameMatchMethodPointcut();
        nameMatchMethodPointcut.setMappedName("gradeUsers");

        return nameMatchMethodPointcut;
    }
}

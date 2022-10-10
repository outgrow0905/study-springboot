package com.springboot.study.ch6.v11;

import com.springboot.study.ch6.v10.MyTransactionAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.PatternMatchUtils;

@Slf4j
@Configuration
public class MyAdvisor {
    @Bean
    public NameMatchMethodPointcut myNameMatchPointcutV2() {
        NameMatchMethodPointcut nameMatchMethodPointcut = new NameMatchMethodPointcut();
        nameMatchMethodPointcut.setMappedName("gradeUsers");
        nameMatchMethodPointcut.setClassFilter((ClassFilter) clazz -> {
            return PatternMatchUtils.simpleMatch("*UserServiceV8", clazz.getSimpleName());
        });

        return nameMatchMethodPointcut;
    }

    @Autowired
    private MyTransactionAdvice myTransactionAdvice;

    @Bean
    public DefaultPointcutAdvisor MyDefaultPointcutAdvisor() {
        DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor();
        defaultPointcutAdvisor.setAdvice(myTransactionAdvice);
        defaultPointcutAdvisor.setPointcut(myNameMatchPointcutV2());
        return defaultPointcutAdvisor;
    }

}

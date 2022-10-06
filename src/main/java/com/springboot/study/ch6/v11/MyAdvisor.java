package com.springboot.study.ch6.v11;

import com.springboot.study.ch6.v10.MyTransactionAdvice;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyAdvisor {
    @Autowired
    private NameMatchMethodPointcut myNameMatchPointcutV2;

    @Autowired
    private MyTransactionAdvice myTransactionAdvice;

    @Bean
    public DefaultPointcutAdvisor MyDefaultPointcutAdvisor() {
        DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor();
        defaultPointcutAdvisor.setAdvice(myTransactionAdvice);
        defaultPointcutAdvisor.setPointcut(myNameMatchPointcutV2);
        return defaultPointcutAdvisor;
    }

}

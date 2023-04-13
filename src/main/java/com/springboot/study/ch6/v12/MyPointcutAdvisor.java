package com.springboot.study.ch6.v12;

import com.springboot.study.ch6.v10.MyTransactionAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MyPointcutAdvisor {
    @Autowired
    private MyTransactionAdvice myTransactionAdvice;

    @Bean
    public AspectJExpressionPointcut myAspectJExpressionPointcut() {
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(
                "@annotation(com.springboot.study.ch6.v8.MyTransactional) " +
                "&& bean(*ServiceV9)");
        return aspectJExpressionPointcut;
    }

    @Bean
    public DefaultPointcutAdvisor myAspectJPointcutAdvisor() {
        DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor();
        defaultPointcutAdvisor.setAdvice(myTransactionAdvice);
        defaultPointcutAdvisor.setPointcut(myAspectJExpressionPointcut());
        return defaultPointcutAdvisor;
    }
}

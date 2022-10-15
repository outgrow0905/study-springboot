package com.springboot.study.ch6.v13;

import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

@Slf4j
@Configuration
public class MyPointcutAdvisorV2 {
    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public AspectJExpressionPointcut myAspectJExpressionPointcutV2() {
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(
                "@annotation(com.springboot.study.ch6.v8.MyTransactional) " +
                "&& bean(*ServiceV10)");
        return aspectJExpressionPointcut;
    }
//
    @Bean
    public TransactionInterceptor myTransactionInterceptor() {
        TransactionInterceptor transactionInterceptor = new TransactionInterceptor();

        Properties transactionAttributes = new Properties();
        transactionAttributes.put("*", "PROPAGATION_REQUIRED, readOnly");
        transactionAttributes.put("gradeUsers", "PROPAGATION_REQUIRED");
        transactionInterceptor.setTransactionAttributes(transactionAttributes);
        transactionInterceptor.setTransactionManager(transactionManager);


        return transactionInterceptor;
    }

    @Bean
    public DefaultPointcutAdvisor myAspectJPointcutAdvisorV2() {
        DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor();
        defaultPointcutAdvisor.setAdvice(myTransactionInterceptor());
        defaultPointcutAdvisor.setPointcut(myAspectJExpressionPointcutV2());
        return defaultPointcutAdvisor;
    }
}

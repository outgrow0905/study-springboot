package com.springboot.advanced.ch4.v19;

import com.springboot.advanced.ch1.trace.LogTrace;
import com.springboot.advanced.ch3.v15.LogAdvice;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutoProxyConfig19 {
    @Bean
    public Advisor advisor19(LogTrace logTraceV5) {
        // pointcut
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.springboot.advanced.ch4.v19..*(..))" +
                "&& !execution(* com.springboot.advanced.ch4.v19..noLog(..))");

        // advice
        LogAdvice advice = new LogAdvice(logTraceV5);

        return new DefaultPointcutAdvisor(pointcut, advice);
    }
}

package com.springboot.advanced.ch3.v17;

import com.springboot.advanced.ch1.trace.LogTrace;
import com.springboot.advanced.ch3.v15.LogAdvice;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanProcessorConfig {
    @Bean
    public BeanPostProcessor logPostProcessor(LogTrace logTraceV5) {
        return new LogBeanPostProcessor(getAdvisor(logTraceV5), "com.springboot.advanced.ch3.v17");
    }

    private Advisor getAdvisor(LogTrace logTraceV5) {
        // pointcut
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*", "save*");

        return new DefaultPointcutAdvisor(pointcut, new LogAdvice(logTraceV5));
    }
}

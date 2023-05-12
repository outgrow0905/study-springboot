package com.springboot.advanced.ch4.v18;

import com.springboot.advanced.ch1.trace.LogTrace;
import com.springboot.advanced.ch3.v15.LogAdvice;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.PatternMatchUtils;

@Configuration
public class AutoProxyConfig {

    @Bean
    public Advisor advisor18(LogTrace logTraceV5) {
        // pointcut
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*", "save*");
        pointcut.setClassFilter(clazz -> PatternMatchUtils.simpleMatch("*V18", clazz.getSimpleName()));

        return new DefaultPointcutAdvisor(pointcut, new LogAdvice(logTraceV5));
    }
}

package com.springboot.aop.ch2.v1;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Slf4j
public class BeanTest {

    ApplicationContext context =
            new AnnotationConfigApplicationContext(Config.class, BeanAspect.class);

    @Test
    void success () {
        Hello hello = context.getBean("hello", Hello.class);
        hello.hello();
    }


    @Configuration
    static class Config {
        @Bean
        public Hello hello() {
            return new Hello();
        }
    }

    static class Hello {
        public void hello(){}
    }

    @Slf4j
    @Aspect
    @EnableAspectJAutoProxy
    static class BeanAspect {
        @Around("bean(hello)")
        public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[bean]: {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }
}

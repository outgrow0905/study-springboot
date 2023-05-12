package com.springboot.advanced.ch4.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.junit.jupiter.api.Assertions.assertThrows;
@Slf4j
public class Basic {

    @Test
    void context() {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfiguration.class);
        A beanA = context.getBean("beanA", A.class);
        beanA.helloA();
        
        assertThrows(NoSuchBeanDefinitionException.class, () -> context.getBean("beanB", B.class));
    }

    @Configuration
    @Slf4j
    static class MyConfiguration {
        @Bean(name = "beanA")
        public A beanA() {
            return new A();
        }
    }

    @Slf4j
    static class A {
        void helloA() {
            log.debug("hello A");
        }
    }

    @Slf4j
    static class B {
        void helloB() {
            log.debug("hello B");
        }
    }
}

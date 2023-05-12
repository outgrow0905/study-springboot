package com.springboot.advanced.ch4.postprocessor;


import static org.junit.jupiter.api.Assertions.assertThrows;

import com.springboot.advanced.ch4.postprocessor.Basic.B;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNotOfRequiredTypeException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
public class PostProcessor {
    @Test
    void context() {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfiguration.class);

        B beanB = context.getBean("beanA", B.class);
        beanB.helloB();

        assertThrows(BeanNotOfRequiredTypeException.class, () -> context.getBean("beanA", A.class));
    }

    @Configuration
    @Slf4j
    static class MyConfiguration {
        @Bean(name = "beanA")
        public A beanA() {
            return new A();
        }

        @Bean
        public BeanPostProcessor postProcessor() {
            return new AtoBPostProcessor();
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

    @Slf4j
    static class AtoBPostProcessor implements BeanPostProcessor {

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {

            log.info("bean: {}, beanName: {}", bean, beanName);

            if (bean instanceof A) {
                return new B();
            }

            return bean;
        }
    }
}

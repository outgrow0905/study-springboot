package com.springboot.study.ch6.v10;

import com.springboot.study.ch4.v7.UserServiceInterfaceV1;
import com.springboot.study.ch6.v8.UserServiceV5;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyProxyFactoryBean {

    @Autowired
    private UserServiceV5 userServiceV5;

    @Autowired
    private MyTransactionAdvice myTransactionAdvice;

    @Autowired
    private NameMatchMethodPointcut myNameMatchPointcut;

    @Bean(name = "userServiceV7")
    public ProxyFactoryBean userServiceProxyFactoryBean() {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor();
        defaultPointcutAdvisor.setAdvice(myTransactionAdvice);
        defaultPointcutAdvisor.setPointcut(myNameMatchPointcut);
        proxyFactoryBean.addAdvisor(defaultPointcutAdvisor);
        proxyFactoryBean.setTarget(userServiceV5);

        return proxyFactoryBean;
    }

    @Bean
    public UserServiceInterfaceV1 userServiceV7() {
        return (UserServiceInterfaceV1)userServiceProxyFactoryBean().getObject();
    }
}

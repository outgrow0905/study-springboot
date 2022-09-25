package com.springboot.study.ch6.beanfactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class MessageBean {

    @Bean
    public Message myMessage1() {
        return Message.newMessage("hello myMessage!");
    }

    @Autowired
    private MessageFactoryBean messageFactoryBean;

    @Bean
    public Message myMessage2() throws Exception {
        messageFactoryBean.setText("message bean by messageFactoryBean");
        return messageFactoryBean.getObject();
    }

}

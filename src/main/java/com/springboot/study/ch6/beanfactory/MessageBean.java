package com.springboot.study.ch6.beanfactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageBean {

    @Bean
    public Message myMessage1() {
        return Message.newMessage("hello myMessage!");
    }


    @Bean(name = "myMessage2")
    public MessageFactory messageFactoryBean() throws Exception {
        MessageFactory messageFactoryBean = new MessageFactory();
        messageFactoryBean.setText("message bean by messageFactoryBean");

        return messageFactoryBean;
    }

    @Bean
    public Message myMessage2() throws Exception {
        return messageFactoryBean().getObject();
    }
}

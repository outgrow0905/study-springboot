package com.springboot.study.ch6.beanfactory;

import lombok.Setter;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Configuration;


@Setter
@Configuration
public class MessageFactoryBean implements FactoryBean<Message> {

    private String text;

    @Override
    public Message getObject() throws Exception {
        return Message.newMessage(this.text);
    }

    @Override
    public Class<? extends Message> getObjectType() {
        return Message.class;
    }
}

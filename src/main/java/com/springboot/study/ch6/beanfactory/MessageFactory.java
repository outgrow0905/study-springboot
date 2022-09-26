package com.springboot.study.ch6.beanfactory;

import lombok.Setter;
import org.springframework.beans.factory.FactoryBean;


@Setter
public class MessageFactory implements FactoryBean<Message> {

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

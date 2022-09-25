package com.springboot.study.ch6.beanfactory;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Message {
    private String text;

    private Message(String text) {
        this.text = text;
    }

    public static Message newMessage(String text) {
        return new Message(text);
    }
}

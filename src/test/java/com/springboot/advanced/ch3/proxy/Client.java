package com.springboot.advanced.ch3.proxy;

public class Client {
    private final Subject subject;

    public Client(Subject subject) {
        this.subject = subject;
    }

    public void execute() {
        subject.operation();
    }
}

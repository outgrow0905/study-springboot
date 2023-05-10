package com.springboot.advanced.ch3.v12;

public class Client {
    private ConcreteLogic concreteLogic;

    public Client(ConcreteLogic concreteLogic) {
        this.concreteLogic = concreteLogic;
    }

    public String operation() {
        return concreteLogic.operation();
    }
}

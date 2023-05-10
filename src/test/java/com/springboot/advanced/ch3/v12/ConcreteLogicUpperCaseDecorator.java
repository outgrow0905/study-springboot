package com.springboot.advanced.ch3.v12;

public class ConcreteLogicUpperCaseDecorator extends ConcreteLogic{

    // ConcreteLogic을 변수로 관리하지 않으면 여러 ConcreteLogic을 체이닝하여 사용할수가 없다.
    private ConcreteLogic concreteLogic;

    public ConcreteLogicUpperCaseDecorator(ConcreteLogic concreteLogic) {
        this.concreteLogic = concreteLogic;
    }

    @Override
    public String operation() {
        String result = concreteLogic.operation();
        return result.toUpperCase();
    }
}

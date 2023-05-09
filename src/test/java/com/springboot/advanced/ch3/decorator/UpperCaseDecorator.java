package com.springboot.advanced.ch3.decorator;

import org.junit.platform.commons.util.StringUtils;

public class UpperCaseDecorator extends Decorator{
    public UpperCaseDecorator(Component component) {
        super(component);
    }

    @Override
    public String operation() {
        String result = component.operation();
        String upperCasedResult = result.toUpperCase();
        return upperCasedResult;
    }
}

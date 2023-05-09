package com.springboot.advanced.ch3.decorator;

public abstract class Decorator implements Component{
    protected final Component component;

    protected Decorator(Component component) {
        this.component = component;
    }
}

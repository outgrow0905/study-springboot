package com.springboot.advanced.ch3.decorator;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class DecoratorPatternTest {
    @Test
    void withoutDecorator() {
        Client client = new Client(new RealComponent());
        client.execute();
    }

    @Test
    void upperCaseDecorator() {
        Client client = new Client(new UpperCaseDecorator(new RealComponent()));
        client.execute();
    }

    @Test
    void upperCaseDecoratorAndTimeDecorator() {
        Client client = new Client(new TimeDecorator(new UpperCaseDecorator(new RealComponent())));
        client.execute();
    }
}

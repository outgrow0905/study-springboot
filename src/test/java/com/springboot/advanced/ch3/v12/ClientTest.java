package com.springboot.advanced.ch3.v12;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ClientTest {
    @Test
    void concreteLogic() {

        Client client = new Client(new ConcreteLogic());
        assertEquals("data", client.operation());
    }

    @Test
    void concreteLogicUpperCaseDecorator() {
        Client client =
            new Client(new ConcreteLogicUpperCaseDecorator(new ConcreteLogic()));
        assertEquals("DATA", client.operation());
    }
}

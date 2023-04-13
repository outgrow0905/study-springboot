package com.springboot.study.ch7.v14;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JaxbReaderTest {
    @Autowired
    private JaxbReader jaxbReader;

    @Test
    void post_construct_annotation() {
        assertEquals(
                "SELECT id, name, visit, \"like\", level FROM users WHERE id = ?",
                jaxbReader.getSql("selectUser"));
    }

}
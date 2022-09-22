package com.springboot.study.ch2.v1;

import com.springboot.study.ch2.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
class UserDaoV1Test {

    @Autowired
    private UserDaoV1 userDaoV1;

    @Test
    void insert_and_update_user() {
        userDaoV1.insertUser(1, "Hello");
        User user = userDaoV1.selectUser(1);
        assertEquals("Hello", user.getName());

        userDaoV1.updateUser(1, "Bye");
        user = userDaoV1.selectUser(1);
        assertEquals("Bye", user.getName());
    }
}
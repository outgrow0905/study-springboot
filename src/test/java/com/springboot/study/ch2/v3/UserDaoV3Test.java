package com.springboot.study.ch2.v3;

import static org.junit.jupiter.api.Assertions.*;

import com.springboot.study.ch2.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class UserDaoV3Test {

    @Autowired
    private UserDaoV3 userDaoV3;

    @Test
    void insert_and_select() {
        userDaoV3.deleteUser(4);
        userDaoV3.insertUser(4, "Hello");
        User user = userDaoV3.selectUser(4);
        assertEquals("Hello", user.getName());
    }

    @Test
    void insert_update_and_select() {
      userDaoV3.deleteUser(4);
      userDaoV3.insertUser(4, "Hello");
      userDaoV3.updateUser(4, "Bye");
      User user = userDaoV3.selectUser(4);
      assertEquals("Bye", user.getName());
    }
}
package com.springboot.study.ch2.v2;

import com.springboot.study.ch2.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class UserDaoV2Test {

    @Autowired
    private UserDaoV2 userDaoV2;

    @Test
    void update_and_select_user() throws Exception{
        userDaoV2.updateUser(1, "Hello");
        User user = userDaoV2.selectUser(1);
        assertEquals("Hello", user.getName());
    }
}
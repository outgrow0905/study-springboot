package com.springboot.study.v1;

import com.springboot.study.model.User;
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
    void update_and_select_user() throws Exception{
        userDaoV1.updateUser(1, "Hello");
        User user = userDaoV1.selectUser(1);
        assertEquals("Hello", user.getName());
    }
}
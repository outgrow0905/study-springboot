package com.springboot.study.ch1;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void update_and_select_user() throws Exception{
        userDao.updateUser(1, "Hello");
        User user = userDao.selectUser(1);
        assertEquals("Hello", user.getName());
    }
}
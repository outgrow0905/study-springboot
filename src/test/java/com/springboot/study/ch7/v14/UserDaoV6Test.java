package com.springboot.study.ch7.v14;

import com.springboot.study.ch4.model.Level;
import com.springboot.study.ch4.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class UserDaoV6Test {
    User user1, user2, user3, user4;

    @Autowired
    private UserDaoInterfaceV2 userDaoV6;

    @BeforeEach
    void setUp() {
        user1 = new User(1, "Kim", 10, 10, Level.BRONZE);
        user2 = new User(2, "Lee", 20, 20, Level.BRONZE);
        user3 = new User(3, "Park", 30, 30, Level.BRONZE);
        user4 = new User(4, "Choi", 40, 40, Level.BRONZE);

        userDaoV6.deleteAll();
        assertEquals(0, userDaoV6.selectCount());
        userDaoV6.insertUser(user1);
        userDaoV6.insertUser(user2);
        userDaoV6.insertUser(user3);
        userDaoV6.insertUser(user4);
        assertEquals(4, userDaoV6.selectCount());
    }

    @Test
    void test() {}
}
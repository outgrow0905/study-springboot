package com.springboot.study.ch4.v4;

import static org.junit.jupiter.api.Assertions.*;

import com.springboot.study.ch4.model.Level;
import com.springboot.study.ch4.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceV1Test {

    User user1, user2, user3, user4;

    @Autowired
    private UserServiceV1 userServiceV1;

    @Autowired
    private UserDaoV4 userDaoV4;

    @BeforeEach
    void setUp() {
        user1 = new User(1, "Kim", 10, 10, Level.BRONZE);
        user2 = new User(2, "Lee", 20, 20, Level.BRONZE);
        user3 = new User(3, "Park", 30, 30, Level.BRONZE);
        user4 = new User(4, "Choi", 40, 40, Level.BRONZE);

        userDaoV4.deleteAll();
        assertEquals(0, userDaoV4.selectCount());
        userDaoV4.insertUser(user1);
        userDaoV4.insertUser(user2);
        userDaoV4.insertUser(user3);
        userDaoV4.insertUser(user4);
        assertEquals(4, userDaoV4.selectCount());
    }

    @Test
    void add() {
        userDaoV4.deleteUser(1);
        user1.setLevel(null);
        userServiceV1.add(user1);
        assertEquals(Level.BRONZE, userDaoV4.selectUser(1).getLevel());
    }

    @Test
    void grade_user() {
        userServiceV1.gradeUser(user1);
        assertEquals(Level.BRONZE, userDaoV4.selectUser(1).getLevel());

        userServiceV1.gradeUser(user2);
        assertEquals(Level.SILVER, userDaoV4.selectUser(2).getLevel());

        userServiceV1.gradeUser(user3);
        assertEquals(Level.GOLD, userDaoV4.selectUser(3).getLevel());

        userServiceV1.gradeUser(user4);
        assertEquals(Level.GOLD, userDaoV4.selectUser(4).getLevel());
    }
}
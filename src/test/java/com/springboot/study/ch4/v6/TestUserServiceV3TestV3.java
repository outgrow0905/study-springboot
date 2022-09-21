package com.springboot.study.ch4.v6;

import com.springboot.study.ch4.model.Level;
import com.springboot.study.ch4.model.User;
import com.springboot.study.ch4.v5.UserDaoV5;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TestUserServiceV3TestV3 {
    User user1, user2, user3, user4;

    @Autowired
    private TestUserServiceV3 testUserServiceV3;

    @Autowired
    private UserDaoV5 userDaoV5;

    @BeforeEach
    void setUp() {
        user1 = new User(1, "Kim", 10, 10, Level.BRONZE);
        user2 = new User(2, "Lee", 20, 20, Level.BRONZE);
        user3 = new User(3, "Park", 30, 30, Level.BRONZE);
        user4 = new User(4, "Choi", 40, 40, Level.BRONZE);

        userDaoV5.deleteAll();
        assertEquals(0, userDaoV5.selectCount());
        userDaoV5.insertUser(user1);
        userDaoV5.insertUser(user2);
        userDaoV5.insertUser(user3);
        userDaoV5.insertUser(user4);
        assertEquals(4, userDaoV5.selectCount());
    }

    @Test
    void grade_users() throws Exception {
        testUserServiceV3.setId(4);
        testUserServiceV3.gradeUsers();

        assertEquals(Level.BRONZE, userDaoV5.selectUser(1).getLevel());
        assertEquals(Level.BRONZE, userDaoV5.selectUser(2).getLevel());
        assertEquals(Level.BRONZE, userDaoV5.selectUser(3).getLevel());
        assertEquals(Level.BRONZE, userDaoV5.selectUser(4).getLevel());
    }

}
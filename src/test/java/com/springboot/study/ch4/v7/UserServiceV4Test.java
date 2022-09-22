package com.springboot.study.ch4.v7;

import com.springboot.study.ch4.model.Level;
import com.springboot.study.ch4.model.User;
import com.springboot.study.ch4.v4.UserLevelDefaultPolicy;
import com.springboot.study.ch4.v6.UserDaoInterface;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class UserServiceV4Test {

    User user1, user2, user3, user4;

    @Autowired
    private UserServiceV4 userServiceV4;

    @Autowired
    private UserDaoInterface userDaoInterface;

    @BeforeEach
    void setUp() {
        userServiceV4.setUserLevelPolicy(new UserLevelDefaultPolicy());

        userDaoInterface.deleteAll();
        assertEquals(0, userDaoInterface.selectCount());

        user1 = new User(1, "Kim", 10, 10, Level.BRONZE);
        user2 = new User(2, "Lee", 20, 20, Level.BRONZE);
        user3 = new User(3, "Park", 30, 30, Level.BRONZE);
        user4 = new User(4, "Choi", 40, 40, Level.BRONZE);

        userDaoInterface.insertUser(user1);
        userDaoInterface.insertUser(user2);
        userDaoInterface.insertUser(user3);
        userDaoInterface.insertUser(user4);
        assertEquals(4, userDaoInterface.selectCount());
    }

    @Test
    void grade_user() throws Exception {
        userServiceV4.gradeUser(user1);
        assertEquals(Level.BRONZE, userDaoInterface.selectUser(1).getLevel());

        userServiceV4.gradeUser(user2);
        assertEquals(Level.SILVER, userDaoInterface.selectUser(2).getLevel());

        userServiceV4.gradeUser(user3);
        assertEquals(Level.GOLD, userDaoInterface.selectUser(3).getLevel());

        userServiceV4.gradeUser(user4);
        assertEquals(Level.GOLD, userDaoInterface.selectUser(4).getLevel());
    }
}
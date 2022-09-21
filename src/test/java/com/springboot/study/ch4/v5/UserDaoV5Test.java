package com.springboot.study.ch4.v5;

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
class UserDaoV5Test {

    @Autowired
    private UserDaoV5 userDaoV5;

    @BeforeEach
    void setUp() {
        userDaoV5.deleteAll();
        assertEquals(0, userDaoV5.selectCount());
        userDaoV5.insertUser(new User(1, "Kim", 10, 10, Level.BRONZE));
        userDaoV5.insertUser(new User(2, "Lee", 20, 20, Level.SILVER));
        userDaoV5.insertUser(new User(3, "Park", 30, 30, Level.GOLD));
        userDaoV5.insertUser(new User(4, "Choi", 40, 40, Level.GOLD));
        assertEquals(4, userDaoV5.selectCount());
    }

    @Test
    void insert_and_select() {
        userDaoV5.deleteUser(4);
        userDaoV5.insertUser(new User(4, "Choi", 40, 40, Level.GOLD));
        User user = userDaoV5.selectUser(4);
        assertEquals("Choi", user.getName());
        assertEquals(40, user.getVisit());
        assertEquals(40, user.getLike());
        assertEquals(Level.GOLD, user.getLevel());
    }

    @Test
    void select_all() {
        userDaoV5.deleteAll();
        assertEquals(0, userDaoV5.selectUserAll().size());
        userDaoV5.insertUser(new User(4, "Choi", 40, 40, Level.GOLD));
        assertEquals(1, userDaoV5.selectUserAll().size());
    }
}
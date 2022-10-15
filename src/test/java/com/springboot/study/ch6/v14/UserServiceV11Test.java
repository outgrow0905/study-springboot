package com.springboot.study.ch6.v14;

import static org.junit.jupiter.api.Assertions.*;

import com.springboot.study.ch4.model.Level;
import com.springboot.study.ch4.model.User;
import com.springboot.study.ch4.v6.UserDaoInterface;
import com.springboot.study.ch4.v7.UserServiceInterfaceV1;
import java.lang.reflect.Proxy;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class UserServiceV11Test {
    User user1, user2, user3, user4;

    @Autowired
    private UserDaoInterface userDaoInterface;

    @Autowired
    private UserServiceInterfaceV2 userServiceV11;

    @Autowired
    private UserServiceInterfaceV2 testUserServiceV11;

    @BeforeEach
    void setUp() {
        user1 = new User(1, "Kim", 10, 10, Level.BRONZE);
        user2 = new User(2, "Lee", 20, 20, Level.BRONZE);
        user3 = new User(3, "Park", 30, 30, Level.BRONZE);
        user4 = new User(4, "Choi", 40, 40, Level.BRONZE);

        userDaoInterface.deleteAll();
        assertEquals(0, userDaoInterface.selectCount());
        userDaoInterface.insertUser(user1);
        userDaoInterface.insertUser(user2);
        userDaoInterface.insertUser(user3);
        userDaoInterface.insertUser(user4);
        assertEquals(4, userDaoInterface.selectCount());
    }

    @Test
    void auto_proxy_created() {
        assertTrue(userServiceV11 instanceof Proxy);
        assertTrue(testUserServiceV11 instanceof Proxy);
    }

    @Test
    void transactional_without_exception() throws Exception {
        // when
        userServiceV11.gradeUsers();

        // then
        assertEquals(Level.BRONZE, userDaoInterface.selectUser(1).getLevel());
        assertEquals(Level.SILVER, userDaoInterface.selectUser(2).getLevel());
        assertEquals(Level.GOLD, userDaoInterface.selectUser(3).getLevel());
        assertEquals(Level.GOLD, userDaoInterface.selectUser(4).getLevel());
    }

    @Test
    void transactional_with_exception() throws Exception {
        // when
        assertThrows(RuntimeException.class, () -> testUserServiceV11.gradeUsers());

        // then
        assertEquals(Level.BRONZE, userDaoInterface.selectUser(1).getLevel());
        assertEquals(Level.BRONZE, userDaoInterface.selectUser(2).getLevel());
        assertEquals(Level.BRONZE, userDaoInterface.selectUser(3).getLevel());
        assertEquals(Level.BRONZE, userDaoInterface.selectUser(4).getLevel());
    }
}
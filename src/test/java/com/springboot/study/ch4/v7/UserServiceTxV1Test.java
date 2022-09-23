package com.springboot.study.ch4.v7;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.springboot.study.ch4.model.Level;
import com.springboot.study.ch4.model.User;
import com.springboot.study.ch4.v4.UserLevelDefaultPolicy;
import com.springboot.study.ch4.v6.UserDaoInterface;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@SpringBootTest
public class UserServiceTxV1Test {

    User user1, user2, user3, user4;

    @Autowired
    private UserDaoInterface userDaoInterface;

    @Autowired
    private PlatformTransactionManager transactionManager;

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
    void transaction_rollback() throws Exception {
        // given
        UserServiceV4 userServiceV4 = new UserServiceV4(userDaoInterface);
        userServiceV4.setUserLevelPolicy(new UserLevelDefaultPolicy());
        UserServiceV4 mockUserServiceV4 = spy(userServiceV4);
        doThrow(new RuntimeException()).when(mockUserServiceV4).gradeUser(user3);

        UserServiceTxV1 userServiceTxV1 = new UserServiceTxV1(transactionManager);
        userServiceTxV1.setUserServiceInterfaceV1(mockUserServiceV4);
        UserServiceTxV1 mockUserServiceTxV1 = spy(userServiceTxV1);

        // when
        mockUserServiceTxV1.gradeUsers();

        // then
        verify(mockUserServiceV4, times(3)).gradeUser(any(User.class));

        // then
        assertEquals(Level.BRONZE, userDaoInterface.selectUser(1).getLevel());
        assertEquals(Level.BRONZE, userDaoInterface.selectUser(2).getLevel());
        assertEquals(Level.BRONZE, userDaoInterface.selectUser(3).getLevel());
        assertEquals(Level.BRONZE, userDaoInterface.selectUser(4).getLevel());
    }
}

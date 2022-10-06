package com.springboot.study.ch6.v10;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;

import com.springboot.study.ch4.model.Level;
import com.springboot.study.ch4.model.User;
import com.springboot.study.ch4.v4.UserLevelDefaultPolicy;
import com.springboot.study.ch4.v6.UserDaoInterface;
import com.springboot.study.ch4.v7.UserServiceInterfaceV1;
import com.springboot.study.ch6.v10.proxyfactorybean.MySay;
import com.springboot.study.ch6.v8.UserServiceV5;
import com.springboot.study.ch6.v9.TransactionProxyFactory;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.aop.Advice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@Slf4j
@SpringBootTest
class MyTransactionAdviceTest {

    User user1, user2, user3, user4;

    @Autowired
    private UserDaoInterface userDaoInterface;

    @Autowired
    private UserServiceV5 userServiceV5;

    @Autowired
    private MyTransactionAdvice myTransactionAdvice;

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
    void proxy_factory_bean_transactional_without_exception() throws Exception {
        // given
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor();
        defaultPointcutAdvisor.setAdvice(myTransactionAdvice);
        NameMatchMethodPointcut nameMatchMethodPointcut = new NameMatchMethodPointcut();
        nameMatchMethodPointcut.setMappedName("gradeUsers");
        defaultPointcutAdvisor.setPointcut(nameMatchMethodPointcut);
        proxyFactoryBean.addAdvisor(defaultPointcutAdvisor);
        proxyFactoryBean.setTarget(userServiceV5);

        UserServiceInterfaceV1 userServiceV7 = (UserServiceInterfaceV1) proxyFactoryBean.getObject();

        // when
        userServiceV7.gradeUsers();

        // then
        assertEquals(Level.BRONZE, userDaoInterface.selectUser(1).getLevel());
        assertEquals(Level.SILVER, userDaoInterface.selectUser(2).getLevel());
        assertEquals(Level.GOLD, userDaoInterface.selectUser(3).getLevel());
        assertEquals(Level.GOLD, userDaoInterface.selectUser(4).getLevel());
    }

    @Test
    @DirtiesContext
    void proxy_factory_bean_transactional_with_exception() throws Exception {
        // given
        userServiceV5.setUserLevelPolicy(new UserLevelDefaultPolicy());
        UserServiceV5 mockUserServiceV5 = spy(userServiceV5);
        doThrow(new RuntimeException("mock exception occur")).when(mockUserServiceV5).gradeUser(user3);

        DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor();
        NameMatchMethodPointcut nameMatchMethodPointcut = new NameMatchMethodPointcut();
        nameMatchMethodPointcut.setMappedName("gradeUsers");
        defaultPointcutAdvisor.setPointcut(nameMatchMethodPointcut);
        defaultPointcutAdvisor.setAdvice(myTransactionAdvice);

        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(mockUserServiceV5);
        proxyFactoryBean.addAdvisor(defaultPointcutAdvisor);

        UserServiceInterfaceV1 userServiceV7 = (UserServiceInterfaceV1) proxyFactoryBean.getObject();

        // when
        userServiceV7.gradeUsers();

        // then
        assertEquals(Level.BRONZE, userDaoInterface.selectUser(1).getLevel());
        assertEquals(Level.BRONZE, userDaoInterface.selectUser(2).getLevel());
        assertEquals(Level.BRONZE, userDaoInterface.selectUser(3).getLevel());
        assertEquals(Level.BRONZE, userDaoInterface.selectUser(4).getLevel());
    }
}
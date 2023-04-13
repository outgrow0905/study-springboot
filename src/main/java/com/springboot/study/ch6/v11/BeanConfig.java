package com.springboot.study.ch6.v11;

import com.springboot.study.ch4.v4.UserLevelDefaultPolicy;
import com.springboot.study.ch4.v6.UserDaoInterface;
import com.springboot.study.ch6.v11.UserServiceV8.TestUserServiceV8;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Autowired
    private UserDaoInterface userDaoInterface;

    @Bean
    public UserServiceV8 userServiceV8() {
        UserServiceV8 userServiceV8 = new UserServiceV8(userDaoInterface, new UserLevelDefaultPolicy());
        return userServiceV8;
    }

    @Bean
    public TestUserServiceV8 testUserServiceV8() {
        TestUserServiceV8 testUserServiceV8 = new TestUserServiceV8(userDaoInterface, new UserLevelDefaultPolicy());
        return testUserServiceV8;
    }
}

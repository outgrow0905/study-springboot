package com.springboot.study.ch6.v12;

import com.springboot.study.ch4.v4.UserLevelDefaultPolicy;
import com.springboot.study.ch4.v6.UserDaoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
public class BeanConfigV2 {
    @Autowired
    private UserDaoInterface userDaoInterface;

    @Bean
    public UserServiceV9 userServiceV9() {
        UserServiceV9 userServiceV9 = new UserServiceV9(userDaoInterface, new UserLevelDefaultPolicy());
        return userServiceV9;
    }

    @Bean
    public UserServiceV9.TestUserServiceV9 testUserServiceV9() {
        UserServiceV9.TestUserServiceV9 testUserServiceV9 = new UserServiceV9.TestUserServiceV9(userDaoInterface, new UserLevelDefaultPolicy());
        return testUserServiceV9;
    }
}
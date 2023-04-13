package com.springboot.study.ch6.v13;

import com.springboot.study.ch4.v4.UserLevelDefaultPolicy;
import com.springboot.study.ch4.v6.UserDaoInterface;
import com.springboot.study.ch6.v12.UserServiceV9;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfigV3 {
    @Autowired
    private UserDaoInterface userDaoInterface;

    @Bean
    public UserServiceV10 userServiceV10() {
        UserServiceV10 userServiceV10 = new UserServiceV10(userDaoInterface, new UserLevelDefaultPolicy());
        return userServiceV10;
    }

    @Bean
    public UserServiceV10.TestUserServiceV10 testUserServiceV10() {
        UserServiceV10.TestUserServiceV10 testUserServiceV10 =
            new UserServiceV10.TestUserServiceV10(userDaoInterface, new UserLevelDefaultPolicy());
        return testUserServiceV10;
    }
}
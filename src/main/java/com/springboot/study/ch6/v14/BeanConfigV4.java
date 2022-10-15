package com.springboot.study.ch6.v14;

import com.springboot.study.ch4.v4.UserLevelDefaultPolicy;
import com.springboot.study.ch4.v6.UserDaoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfigV4 {
    @Autowired
    private UserDaoInterface userDaoInterface;

    @Bean
    public UserServiceV11 userServiceV11() {
        UserServiceV11 userServiceV10 = new UserServiceV11(userDaoInterface, new UserLevelDefaultPolicy());
        return userServiceV10;
    }

    @Bean
    public UserServiceV11.TestUserServiceV11 testUserServiceV11() {
        UserServiceV11.TestUserServiceV11 testUserServiceV10 =
            new UserServiceV11.TestUserServiceV11(userDaoInterface, new UserLevelDefaultPolicy());
        return testUserServiceV10;
    }
}
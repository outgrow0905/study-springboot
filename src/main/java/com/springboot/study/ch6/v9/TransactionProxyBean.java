package com.springboot.study.ch6.v9;

import com.springboot.study.ch4.v4.UserLevelDefaultPolicy;
import com.springboot.study.ch4.v7.UserServiceInterfaceV1;
import com.springboot.study.ch6.v8.UserServiceV5;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
public class TransactionProxyBean {

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private UserServiceV5 userServiceV5;

    @Bean(name = "userServiceV6")
    public TransactionProxyFactory userServiceTransactionProxyFactory() {
        TransactionProxyFactory factory = new TransactionProxyFactory();
        factory.setTransactionManager(transactionManager);
        userServiceV5.setUserLevelPolicy(new UserLevelDefaultPolicy());
        factory.setTarget(userServiceV5);
        factory.setServiceInterface(UserServiceInterfaceV1.class);
        return factory;
    }

    @Bean
    public UserServiceInterfaceV1 userServiceV6() throws Exception {
        return (UserServiceInterfaceV1)userServiceTransactionProxyFactory().getObject();
    }
}

package com.springboot.study.ch4.v7;

import com.springboot.study.ch4.model.Level;
import com.springboot.study.ch4.model.User;
import com.springboot.study.ch4.v4.UserLevelPolicy;
import com.springboot.study.ch4.v6.UserDaoInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;

@Slf4j
@Service
public class UserServiceTxV1 implements UserServiceInterfaceV1 {
    private UserServiceInterfaceV1 userServiceInterfaceV1;
    private final PlatformTransactionManager transactionManager;

    @Autowired
    public UserServiceTxV1(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void setUserServiceInterfaceV1(UserServiceInterfaceV1 userServiceInterfaceV1) {
        this.userServiceInterfaceV1 = userServiceInterfaceV1;
    }

    @Override
    public List<User> selectUserAll() {
        return userServiceInterfaceV1.selectUserAll();
    }

    @Override
    public void gradeUsers() throws Exception {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            userServiceInterfaceV1.gradeUsers();
            transactionManager.commit(status);
        }  catch (Exception e) {
            transactionManager.rollback(status);
        }
    }

    @Override
    public void gradeUser(User user) {
        userServiceInterfaceV1.gradeUser(user);
    }
}

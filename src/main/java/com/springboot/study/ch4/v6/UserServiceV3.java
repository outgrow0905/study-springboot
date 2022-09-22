package com.springboot.study.ch4.v6;

import com.springboot.study.ch4.model.Level;
import com.springboot.study.ch4.model.User;
import com.springboot.study.ch4.v4.UserLevelPolicy;
import com.springboot.study.ch4.v5.UserDaoV5;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceV3 {
    private final UserDaoInterface userDaoInterface;
    private final PlatformTransactionManager transactionManager;
    private final UserLevelPolicy userLevelPolicy;

    public List<User> selectUserAll() {
        return userDaoInterface.selectUserAll();
    }

    public void gradeUsers() throws Exception {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            List<User> users = selectUserAll();
            for (User user : users) {
                gradeUser(user);
            }
            transactionManager.commit(status);
        }  catch (Exception e) {
            transactionManager.rollback(status);
        }
    }

    public void gradeUser(User user) {
        Level nextLevel = userLevelPolicy.gradeLevel(user);
        if (user.getLevel() != nextLevel) {
            userDaoInterface.updateLevel(user.getId(), nextLevel);
        }
    }
}

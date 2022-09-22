package com.springboot.study.ch4.v6;

import com.springboot.study.ch4.model.User;
import com.springboot.study.ch4.v4.UserLevelPolicy;
import com.springboot.study.ch4.v5.UserDaoV5;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;

@Setter
@Service
public class TestUserServiceV3 extends UserServiceV3 {

    private int id;

    @Autowired
    PlatformTransactionManager transactionManager;

    @Autowired
    public TestUserServiceV3(UserDaoInterface userDaoInterface, PlatformTransactionManager transactionManager, UserLevelPolicy userLevelPolicy) {
        super(userDaoInterface, transactionManager, userLevelPolicy);
    }

    @Override
    public void gradeUsers() throws Exception {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            List<User> users = selectUserAll();
            for (User user : users) {
                if (user.getId() == this.id) {
                    throw new RuntimeException("exception occur id: " + this.id);
                }
                gradeUser(user);
            }
            transactionManager.commit(status);
        }  catch (Exception e) {
            transactionManager.rollback(status);
        }
    }
}

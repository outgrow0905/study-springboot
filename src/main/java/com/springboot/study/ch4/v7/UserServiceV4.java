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
public class UserServiceV4 implements UserServiceInterfaceV1  {
    private final UserDaoInterface userDaoInterface;
    private UserLevelPolicy userLevelPolicy;

    @Autowired
    public UserServiceV4(UserDaoInterface userDaoInterface) {
        this.userDaoInterface = userDaoInterface;
    }

    public void setUserLevelPolicy(UserLevelPolicy userLevelPolicy) {
        this.userLevelPolicy = userLevelPolicy;
    }

    @Override
    public List<User> selectUserAll() {
        return userDaoInterface.selectUserAll();
    }

    @Override
    public void gradeUsers() throws Exception {
        List<User> users = selectUserAll();
        log.info("users: {}", users);
        for (User user : users) {
            log.info("user: {}", user);
            gradeUser(user);
        }
    }

    @Override
    public void gradeUser(User user) {
        log.info("gradeUser user: {}", user);
        log.info("userLevelPolicy == null : {}", userLevelPolicy == null);
        log.info("userDaoInterface == null : {}", userDaoInterface == null);
        Level nextLevel = userLevelPolicy.gradeLevel(user);
        log.info("user.getLevel() != nextLevel: {}", user.getLevel() != nextLevel);
        if (user.getLevel() != nextLevel) {
            userDaoInterface.updateLevel(user.getId(), nextLevel);
        }
    }
}

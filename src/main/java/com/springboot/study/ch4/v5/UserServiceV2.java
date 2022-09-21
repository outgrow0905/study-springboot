package com.springboot.study.ch4.v5;

import com.springboot.study.ch4.model.Level;
import com.springboot.study.ch4.model.User;
import com.springboot.study.ch4.v4.UserDaoV4;
import com.springboot.study.ch4.v4.UserLevelPolicy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Service
//@RequiredArgsConstructor
public class UserServiceV2 {
    private final UserDaoV5 userDaoV5;
    private final DataSource dataSource;
    private final UserLevelPolicy userLevelPolicy;

    @Autowired
    public UserServiceV2(UserDaoV5 userDaoV5, DataSource dataSource, UserLevelPolicy userLevelPolicy) {
        this.userDaoV5 = userDaoV5;
        this.userLevelPolicy = userLevelPolicy;
        this.dataSource = dataSource;
    }

    public void add(User user) {
        if (user.getLevel() == null) {
            user.setLevel(Level.BRONZE);
        }
        userDaoV5.insertUser(user);
    }

    public List<User> selectUserAll() {
        return userDaoV5.selectUserAll();
    }


    public void gradeUsers() throws Exception {
        TransactionSynchronizationManager.initSynchronization();
        Connection connection = DataSourceUtils.getConnection(dataSource);
        connection.setAutoCommit(false);

        try {
            List<User> users = selectUserAll();
            for (User user : users) {
                gradeUser(user);
            }
            connection.commit();
        }  catch (Exception e) {
            connection.rollback();
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
            TransactionSynchronizationManager.unbindResource(dataSource);
            TransactionSynchronizationManager.clearSynchronization();
        }
    }

    public void gradeUser(User user) {
        Level nextLevel = userLevelPolicy.gradeLevel(user);
        if (user.getLevel() != nextLevel) {
            userDaoV5.updateLevel(user.getId(), nextLevel);
        }
    }
}

package com.springboot.study.ch4.v5;

import com.springboot.study.ch4.model.User;
import com.springboot.study.ch4.v4.UserLevelPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;


@Service
public class TestUserServiceV2 extends UserServiceV2 {

    private int id;
    private DataSource dataSource;

    public void setId(int id) {
        this.id = id;
    }

    @Autowired
    public TestUserServiceV2(UserDaoV5 userDaoV5, DataSource dataSource, UserLevelPolicy userLevelPolicy) {
        super(userDaoV5, dataSource, userLevelPolicy);
        this.dataSource = dataSource;
    }

    @Override
    public void gradeUsers() throws Exception {
        TransactionSynchronizationManager.initSynchronization();
        Connection connection = DataSourceUtils.getConnection(dataSource);
        connection.setAutoCommit(false);

        try {
            List<User> users = selectUserAll();
            for (User user : users) {
                if (user.getId() == this.id) {
                    throw new RuntimeException("exception occur id: " + this.id);
                }
                gradeUser(user);
            }
        }  catch (Exception sqlException) {
            connection.rollback();
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
            TransactionSynchronizationManager.unbindResource(dataSource);
            TransactionSynchronizationManager.clearSynchronization();
        }
    }
}

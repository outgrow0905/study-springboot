package com.springboot.study.ch6.v14;

import com.springboot.study.ch4.model.Level;
import com.springboot.study.ch4.model.User;
import com.springboot.study.ch4.v4.UserLevelPolicy;
import com.springboot.study.ch4.v6.UserDaoInterface;
import com.springboot.study.ch4.v7.UserServiceInterfaceV1;
import com.springboot.study.ch6.v8.MyTransactional;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class UserServiceV11 implements UserServiceInterfaceV2 {
    private UserDaoInterface userDaoInterface;
    private UserLevelPolicy userLevelPolicy;

    @Autowired
    public UserServiceV11(UserDaoInterface userDaoInterface, UserLevelPolicy userLevelPolicy) {
        this.userDaoInterface = userDaoInterface;
        this.userLevelPolicy = userLevelPolicy;
    }

    @Override
    public List<User> selectUserAll() {
        return userDaoInterface.selectUserAll();
    }


    @Override
    public void gradeUsers() throws Exception {
        List<User> users = selectUserAll();
        for (User user : users) {
            gradeUser(user);
        }
    }

    @Override
    public void gradeUser(User user) {
        Level nextLevel = userLevelPolicy.gradeLevel(user);
        if (user.getLevel() != nextLevel) {
            userDaoInterface.updateLevel(user.getId(), nextLevel);
        }
    }


    static class TestUserServiceV11 extends UserServiceV11 {

        public TestUserServiceV11(UserDaoInterface userDaoInterface,
                                 UserLevelPolicy userLevelPolicy) {
            super(userDaoInterface, userLevelPolicy);
        }

        @Override
        public void gradeUser(User user) {
            if (user.getId() == 3) {
                throw new RuntimeException("error occur. userId: " + user.getId());
            }
            super.gradeUser(user);
        }
    }
}

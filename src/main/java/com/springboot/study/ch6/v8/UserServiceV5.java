package com.springboot.study.ch6.v8;

import com.springboot.study.ch4.model.Level;
import com.springboot.study.ch4.model.User;
import com.springboot.study.ch4.v4.UserLevelPolicy;
import com.springboot.study.ch4.v6.UserDaoInterface;
import com.springboot.study.ch4.v7.UserServiceInterfaceV1;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceV5 implements UserServiceInterfaceV1 {
    private final UserDaoInterface userDaoInterface;
    private UserLevelPolicy userLevelPolicy;

    @Autowired
    public UserServiceV5(UserDaoInterface userDaoInterface) {
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
    @MyTransactional
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
}

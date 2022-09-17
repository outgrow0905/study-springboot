package com.springboot.study.ch4.v4;

import com.springboot.study.ch4.model.Level;
import com.springboot.study.ch4.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceV1 {
    private final UserDaoV4 userDaoV4;
    private final UserLevelPolicy userLevelPolicy;

    public void add(User user) {
        if (user.getLevel() == null) {
            user.setLevel(Level.BRONZE);
        }
        userDaoV4.insertUser(user);
    }

    public void gradeUser(User user) {
        Level nextLevel = userLevelPolicy.gradeLevel(user);
        if (user.getLevel() != nextLevel) {
            userDaoV4.updateLevel(user.getId(), nextLevel);
        }
    }
}

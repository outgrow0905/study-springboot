package com.springboot.study.ch7.v14;


import com.springboot.study.ch4.model.Level;
import com.springboot.study.ch4.model.User;

import java.util.List;

public interface UserDaoInterfaceV2 {
    User selectUser(int id);
    List<User> selectUserAll();
    void insertUser(User user);
    void updateLevel(int id, Level level);
    void deleteUser(int id);
    void deleteAll();
    int selectCount();
}

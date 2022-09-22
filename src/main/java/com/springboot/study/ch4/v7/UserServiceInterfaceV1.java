package com.springboot.study.ch4.v7;

import com.springboot.study.ch4.model.User;

import java.util.List;

public interface UserServiceInterfaceV1 {
    List<User> selectUserAll();
    void gradeUsers() throws Exception;
    void gradeUser(User user);
}

package com.springboot.study.ch6.v14;

import com.springboot.study.ch4.model.User;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public interface UserServiceInterfaceV2 {
    List<User> selectUserAll();
    @Transactional
    void gradeUsers() throws Exception;
    void gradeUser(User user);
}

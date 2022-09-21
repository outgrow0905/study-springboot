package com.springboot.study.ch4.v6;


import com.springboot.study.ch4.model.Level;
import com.springboot.study.ch4.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

public interface UserDaoInterface {
    User selectUser(int id);
    List<User> selectUserAll();
    void insertUser(User user);
    void updateLevel(int id, Level level);
    void deleteUser(int id);
    void deleteAll();
    int selectCount();
}

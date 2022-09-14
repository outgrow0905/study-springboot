package com.springboot.study.ch2.v3;

import com.springboot.study.ch2.model.User;
import com.springboot.study.ch2.v2.JdbcContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserDaoV3 {

    private final JdbcTemplate myJdbcTemplate;

    public User selectUser(int id) {
        return myJdbcTemplate.queryForObject("SELECT id, name FROM users WHERE id = ?", (resultSet, rowNum) -> {
            User user = new User();
            user.setId(id);
            user.setName(resultSet.getString(2));
            return user;
        }, id);
    }

  public void insertUser(int id, String name) {
        myJdbcTemplate.update("INSERT INTO users (id, name) VALUES (?, ?)", id, name);
   }


  public void updateUser(int id, String name) {
    myJdbcTemplate.update("UPDATE users SET name = ? WHERE id = ?", name, id);
  }

  public void deleteUser(int id) {
    myJdbcTemplate.update("DELETE FROM users WHERE id = ?", id);
  }
}

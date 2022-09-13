package com.springboot.study.ch2.v3;

import com.springboot.study.ch2.model.User;
import com.springboot.study.ch2.v2.JdbcContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserDaoV3 {

    private final JdbcTemplate myJdbcTemplate;

    private final JdbcContext jdbcContext;

    public User selectUser(int id) {
      String result;
      User user = null;
      result = jdbcContext.select(connection -> {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
        preparedStatement.setInt(1, id);
        return preparedStatement;
      });

      if (StringUtils.hasText(result)) {
        user = new User();
        user.setId(id);
        user.setName(result);
      }
      return user;
    }



  public void insertUser(int id, String name) {
        myJdbcTemplate.update(
            (PreparedStatementCreator) con -> {
                PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO users (id, name) VALUES (?, ?)");
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, name);

                return preparedStatement;
            });
   }


  public void updateUser(int id, String name) {
    myJdbcTemplate.update(
        (PreparedStatementCreator) con -> {
          PreparedStatement preparedStatement = con.prepareStatement("UPDATE users SET name = ? WHERE id = ?");
          preparedStatement.setString(1, name);
          preparedStatement.setInt(2, id);

          return preparedStatement;
        });
  }

  public void deleteUser(int id) {
    myJdbcTemplate.update(
        (PreparedStatementCreator) con -> {
          PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM users WHERE id = ?");
          preparedStatement.setInt(1, id);

          return preparedStatement;
        });
  }
}

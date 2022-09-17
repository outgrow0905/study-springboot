package com.springboot.study.ch4.v4;


import com.springboot.study.ch4.model.Level;
import com.springboot.study.ch4.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserDaoV4 {

    private final JdbcTemplate myJdbcTemplate;

    public User selectUser(int id) {
        return myJdbcTemplate.queryForObject("SELECT id, name, visit, \"like\", level FROM users WHERE id = ?", (resultSet, rowNum) -> {
            User user = new User();
            user.setId(id);
            user.setName(resultSet.getString(2));
            user.setVisit(resultSet.getInt(3));
            user.setLike(resultSet.getInt(4));
            user.setLevel(Level.valueOf(resultSet.getInt(5)));
            return user;
        }, id);
    }

  public void insertUser(User user) {
        myJdbcTemplate.update("INSERT INTO users (id, name, visit, \"like\", level) VALUES (?, ?, ?, ?, ?)",
            user.getId(), user.getName(), user.getVisit(), user.getLike(), user.getLevel().getValue());
   }


  public void updateLevel(int id, Level level) {
    myJdbcTemplate.update("UPDATE users SET level = ? WHERE id = ?", level.getValue(), id);
  }

  public void deleteUser(int id) {
    myJdbcTemplate.update("DELETE FROM users WHERE id = ?", id);
  }

  public void deleteAll() {
      myJdbcTemplate.update("DELETE FROM users");
  }

  public int selectCount() {
    return myJdbcTemplate.queryForObject("SELECT COUNT(*) FROM users", Integer.class);
  }
}

package com.springboot.study.ch4.v6;

import com.springboot.study.ch4.model.Level;
import com.springboot.study.ch4.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDaoByJdbc implements UserDaoInterface {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User selectUser(int id) {
        return jdbcTemplate.queryForObject("SELECT id, name, visit, \"like\", level FROM users WHERE id = ?", (resultSet, rowNum) -> {
            User user = new User();
            user.setId(id);
            user.setName(resultSet.getString(2));
            user.setVisit(resultSet.getInt(3));
            user.setLike(resultSet.getInt(4));
            user.setLevel(Level.valueOf(resultSet.getInt(5)));
            return user;
        }, id);
    }

    @Override
    public List<User> selectUserAll() {
        return jdbcTemplate.query("SELECT id, name, visit, \"like\", level FROM users", (resultSet, rowNum) -> {
            User user = new User();
            user.setId(resultSet.getInt(1));
            user.setName(resultSet.getString(2));
            user.setVisit(resultSet.getInt(3));
            user.setLike(resultSet.getInt(4));
            user.setLevel(Level.valueOf(resultSet.getInt(5)));
            return user;
        });
    }

    @Override
    public void insertUser(User user) {
        jdbcTemplate.update("INSERT INTO users (id, name, visit, \"like\", level) VALUES (?, ?, ?, ?, ?)",
                user.getId(), user.getName(), user.getVisit(), user.getLike(), user.getLevel().getValue());
    }

    @Override
    public void updateLevel(int id, Level level) {
        jdbcTemplate.update("UPDATE users SET level = ? WHERE id = ?", level.getValue(), id);
    }

    @Override
    public void deleteUser(int id) {
        jdbcTemplate.update("DELETE FROM users WHERE id = ?", id);
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM users");
    }

    @Override
    public int selectCount() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users", Integer.class);
    }

}

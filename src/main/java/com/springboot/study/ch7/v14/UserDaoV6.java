package com.springboot.study.ch7.v14;

import com.springboot.study.ch4.model.Level;
import com.springboot.study.ch4.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserDaoV6 implements UserDaoInterfaceV2 {

    private final JdbcTemplate jdbcTemplate;
    private final SqlReader jaxbReader;

    @Override
    public User selectUser(int id) {
        return jdbcTemplate.queryForObject(jaxbReader.getSql("selectUser"), (resultSet, rowNum) -> {
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
        return jdbcTemplate.query(jaxbReader.getSql("selectUserAll"), (resultSet, rowNum) -> {
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
        jdbcTemplate.update(jaxbReader.getSql("insertUser"),
                user.getId(), user.getName(), user.getVisit(), user.getLike(), user.getLevel().getValue());
    }

    @Override
    public void updateLevel(int id, Level level) {
        jdbcTemplate.update(jaxbReader.getSql("updateLevel"), level.getValue(), id);
    }

    @Override
    public void deleteUser(int id) {
        jdbcTemplate.update(jaxbReader.getSql("deleteUser"), id);
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update(jaxbReader.getSql("deleteAll"));
    }

    @Override
    public int selectCount() {
        return jdbcTemplate.queryForObject(jaxbReader.getSql("selectCount"), Integer.class);
    }
}

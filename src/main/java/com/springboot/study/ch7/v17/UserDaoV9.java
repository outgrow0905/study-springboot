package com.springboot.study.ch7.v17;

import com.springboot.study.ch4.model.Level;
import com.springboot.study.ch4.model.User;
import com.springboot.study.ch7.v14.UserDaoInterfaceV2;
import com.springboot.study.ch7.v15.SqlService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserDaoV9 implements UserDaoInterfaceV2 {

    private final JdbcTemplate jdbcTemplate;
    private final SqlService myOxmSqlServiceV2;

    @Override
    public User selectUser(int id) {
        return jdbcTemplate.queryForObject(myOxmSqlServiceV2.getSql("selectUser"), (resultSet, rowNum) -> {
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
        return jdbcTemplate.query(myOxmSqlServiceV2.getSql("selectUserAll"), (resultSet, rowNum) -> {
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
        jdbcTemplate.update(myOxmSqlServiceV2.getSql("insertUser"),
                user.getId(), user.getName(), user.getVisit(), user.getLike(), user.getLevel().getValue());
    }

    @Override
    public void updateLevel(int id, Level level) {
        jdbcTemplate.update(myOxmSqlServiceV2.getSql("updateLevel"), level.getValue(), id);
    }

    @Override
    public void deleteUser(int id) {
        jdbcTemplate.update(myOxmSqlServiceV2.getSql("deleteUser"), id);
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update(myOxmSqlServiceV2.getSql("deleteAll"));
    }

    @Override
    public int selectCount() {
        return jdbcTemplate.queryForObject(myOxmSqlServiceV2.getSql("selectCount"), Integer.class);
    }
}

package com.springboot.study.ch7.v16;

import com.springboot.study.ch4.model.Level;
import com.springboot.study.ch4.model.User;
import com.springboot.study.ch7.v14.UserDaoInterfaceV2;
import com.springboot.study.ch7.v15.SqlService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserDaoV8 implements UserDaoInterfaceV2 {

    private final JdbcTemplate jdbcTemplate;
    private final SqlService myOxmSqlService;

    @Override
    public User selectUser(int id) {
        return jdbcTemplate.queryForObject(myOxmSqlService.getSql("selectUser"), (resultSet, rowNum) -> {
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
        return jdbcTemplate.query(myOxmSqlService.getSql("selectUserAll"), (resultSet, rowNum) -> {
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
        jdbcTemplate.update(myOxmSqlService.getSql("insertUser"),
                user.getId(), user.getName(), user.getVisit(), user.getLike(), user.getLevel().getValue());
    }

    @Override
    public void updateLevel(int id, Level level) {
        jdbcTemplate.update(myOxmSqlService.getSql("updateLevel"), level.getValue(), id);
    }

    @Override
    public void deleteUser(int id) {
        jdbcTemplate.update(myOxmSqlService.getSql("deleteUser"), id);
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update(myOxmSqlService.getSql("deleteAll"));
    }

    @Override
    public int selectCount() {
        return jdbcTemplate.queryForObject(myOxmSqlService.getSql("selectCount"), Integer.class);
    }
}

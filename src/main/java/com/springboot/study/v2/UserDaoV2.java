package com.springboot.study.v2;

import com.springboot.study.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.PreparedStatement;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserDaoV2 {

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

    public void updateUser(int id, String name) {
        jdbcContext.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET name = ? WHERE id = ?");
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);

            return preparedStatement;
        });
    }
}

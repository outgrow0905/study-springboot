package com.springboot.study.ch2.v3;

import com.springboot.study.ch2.model.Department;
import com.springboot.study.ch2.model.User;
import com.springboot.study.ch2.v2.JdbcContext;
import java.sql.PreparedStatement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Slf4j
@Repository
@RequiredArgsConstructor
public class DepartmentDaoV3 {
    private final JdbcContext jdbcContext;

    public User insertDepartment(int id, String name) {
        User user = null;
        jdbcContext.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("IINSERT INTO departments (id, name) VALUES (?, ?)");
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            return preparedStatement;
        });
        return user;
    }

    public User deleteDepartment(int id) {
        User user = null;
        jdbcContext.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM departments WHERE id = ?");
            preparedStatement.setInt(1, id);
            return preparedStatement;
        });
        return user;
    }

    public void updateDepartment(int id, String name) {
        jdbcContext.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE departments SET name = ? WHERE id = ?");
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);

            return preparedStatement;
        });
    }
}

package com.springboot.study.v2;

import com.springboot.study.model.Department;
import com.springboot.study.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@Repository
@RequiredArgsConstructor
public class DepartmentDaoV2 {
    private final JdbcContext jdbcContext;

    public Department selectDepartment(int id) {
        String result;
        Department department = null;
        result = jdbcContext.select(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM departments WHERE id = ?");
            preparedStatement.setInt(1, id);
            return preparedStatement;
        });

        if (StringUtils.hasText(result)) {
            department = new Department();
            department.setId(id);
            department.setName(result);
        }
        return department;
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

package com.springboot.study.ch2.v3;

import com.springboot.study.ch2.model.Department;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class DepartmentDaoV3 {
    private final JdbcTemplate myJdbcTemplate;

    public Department selectDepartment(int id) {
        return myJdbcTemplate.queryForObject("SELECT id, name FROM departments WHERE id = ?", (resultSet, rowNum) -> {
            Department department = new Department();
            department.setId(id);
            department.setName(resultSet.getString(2));
            return department;
        }, id);
    }

    public void insertDepartment(int id, String name) {
        myJdbcTemplate.update("INSERT INTO departments (id, name) VALUES (?, ?)", id, name);
    }


    public void updateDepartment(int id, String name) {
        myJdbcTemplate.update("UPDATE departments SET name = ? WHERE id = ?", name, id);
    }

    public void deleteDepartment(int id) {
        myJdbcTemplate.update("DELETE FROM departments WHERE id = ?", id);
    }
}

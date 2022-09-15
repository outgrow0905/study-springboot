package com.springboot.study.ch2.v3;

import com.springboot.study.ch2.model.Department;
import com.springboot.study.ch2.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
class DepartmentDaoV3Test {

    @Autowired
    private DepartmentDaoV3 departmentDaoV3;

    @Test
    void insert_and_select() {
        departmentDaoV3.deleteDepartment(4);
        departmentDaoV3.insertDepartment(4, "Hello");
        Department department = departmentDaoV3.selectDepartment(4);
        assertEquals("Hello", department.getName());
    }

    @Test
    void insert_update_and_select() {
      departmentDaoV3.deleteDepartment(4);
      departmentDaoV3.insertDepartment(4, "Hello");
      departmentDaoV3.updateDepartment(4, "Bye");
      Department department = departmentDaoV3.selectDepartment(4);
      assertEquals("Bye", department.getName());
    }
}
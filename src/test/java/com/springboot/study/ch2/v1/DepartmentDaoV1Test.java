package com.springboot.study.ch2.v1;

import com.springboot.study.ch2.model.Department;
import com.springboot.study.ch2.model.User;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
class DepartmentDaoV1Test {

    @Autowired
    private DepartmentDaoV1 departmentDaoV1;

    @Test
    void insert_and_update_user() {
        departmentDaoV1.insertDepartment(1, "Hello");
        Department department = departmentDaoV1.selectDepartment(1);
        assertEquals("Hello", department.getName());

        departmentDaoV1.updateDepartment(1, "Bye");
        department = departmentDaoV1.selectDepartment(1);
        assertEquals("Bye", department.getName());
    }
}
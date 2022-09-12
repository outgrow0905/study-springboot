package com.springboot.study.v1;

import com.springboot.study.model.Department;
import lombok.extern.slf4j.Slf4j;
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
    void update_and_select_department() throws Exception{
        departmentDaoV1.updateDepartment(1, "Hello");
        Department department = departmentDaoV1.selectDepartment(1);
        assertEquals("Hello", department.getName());
    }
}
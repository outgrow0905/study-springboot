package com.springboot.study.ch2.v2;

import com.springboot.study.ch2.model.Department;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
class DepartmentDaoV2Test {

    @Autowired
    private DepartmentDaoV2 departmentDaoV2;

    @Test
    void update_and_select_department() throws Exception{
        departmentDaoV2.updateDepartment(1, "Hello");
        Department department = departmentDaoV2.selectDepartment(1);
        assertEquals("Hello", department.getName());
    }
}
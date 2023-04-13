package com.springboot.study.ch7.v14;

import com.springboot.study.ch7.v14.jaxb.SqlMap;
import com.springboot.study.ch7.v14.jaxb.SqlType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class SqlMapTest {

    @Test
    void jaxb_unmarshall() throws JAXBException {
        String classPath = SqlMap.class.getPackage().getName();
        JAXBContext context = JAXBContext.newInstance(classPath);

        Unmarshaller unmarshaller = context.createUnmarshaller();
        SqlMap sqlMap = (SqlMap) unmarshaller.unmarshal(new File("./src/main/resources/ch7/jaxb/sqlMap.xml"));

        List<SqlType> sqlTypeList = sqlMap.getSql();
        assertEquals("add", sqlTypeList.get(0).getKey());
        assertEquals("insert", sqlTypeList.get(0).getValue());
        assertEquals("get", sqlTypeList.get(1).getKey());
        assertEquals("get", sqlTypeList.get(1).getValue());
        assertEquals("delete", sqlTypeList.get(2).getKey());
        assertEquals("delete", sqlTypeList.get(2).getValue());
    }
}
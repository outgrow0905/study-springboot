package com.springboot.study.ch7.v16;

import static org.junit.jupiter.api.Assertions.*;

import com.springboot.study.ch7.v14.jaxb.ObjectFactory;
import com.springboot.study.ch7.v14.jaxb.SqlMap;
import com.springboot.study.ch7.v14.jaxb.SqlType;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import javax.xml.transform.stream.StreamSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.XmlMappingException;

@Slf4j
@SpringBootTest
class OxmBeanConfigTest {
    @Autowired
    private Unmarshaller jaxbUnMarshaller;

    @Test
    void oxm_jaxb2_unmarshal() throws IOException {
        SqlMap sqlMap = (SqlMap)jaxbUnMarshaller.unmarshal(
            new StreamSource(new FileInputStream("./src/main/resources/ch7/jaxb/sqlMap.xml")));

        List<SqlType> sqlTypeList = sqlMap.getSql();
        assertEquals("add", sqlTypeList.get(0).getKey());
        assertEquals("insert", sqlTypeList.get(0).getValue());
        assertEquals("get", sqlTypeList.get(1).getKey());
        assertEquals("get", sqlTypeList.get(1).getValue());
        assertEquals("delete", sqlTypeList.get(2).getKey());
        assertEquals("delete", sqlTypeList.get(2).getValue());
    }
}
package com.springboot.study.ch7.v15;

import com.springboot.study.ch7.v14.SqlNotFoundException;
import com.springboot.study.ch7.v14.jaxb.SqlMap;
import com.springboot.study.ch7.v14.jaxb.SqlType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

@Slf4j
@Component
public class XmlSqlLoader implements SqlLoader {

    private static String DEFAULT_FILE_NAME = "user.xml";
    private String fileName = DEFAULT_FILE_NAME;

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void loadSql(SqlRegistry sqlRegistry) {
        String classPath = SqlMap.class.getPackage().getName();

        try {
            JAXBContext context = JAXBContext.newInstance(classPath);

            Unmarshaller unmarshaller = context.createUnmarshaller();
            SqlMap sqlMap = (SqlMap) unmarshaller.unmarshal(new File("./src/main/resources/ch7/" + this.fileName));
            for (SqlType sqlType : sqlMap.getSql()) {
                sqlRegistry.saveSql(sqlType.getKey(), sqlType.getValue());
            }
        } catch (JAXBException e) {
            log.error(e + "");
            throw new SqlNotFoundException();
        }
    }
}

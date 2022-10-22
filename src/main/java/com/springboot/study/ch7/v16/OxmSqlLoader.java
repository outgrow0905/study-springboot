package com.springboot.study.ch7.v16;

import com.springboot.study.ch7.v14.SqlNotFoundException;
import com.springboot.study.ch7.v14.jaxb.SqlMap;
import com.springboot.study.ch7.v14.jaxb.SqlType;
import com.springboot.study.ch7.v15.SqlLoader;
import com.springboot.study.ch7.v15.SqlRegistry;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.oxm.Unmarshaller;

@Slf4j
public class OxmSqlLoader implements SqlLoader {

    private static final String DEFAULT_FILE_NAME = "user.xml";
    private String fileName = DEFAULT_FILE_NAME;
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    private Unmarshaller unmarshaller;

    public void setUnmarshaller(Unmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }

    public void loadSql(SqlRegistry sqlRegistry) {
        try {
            SqlMap sqlMap = (SqlMap) unmarshaller.unmarshal(new StreamSource(new FileInputStream("./src/main/resources/ch7/" + fileName)));
            for (SqlType sqlType : sqlMap.getSql()) {
                sqlRegistry.saveSql(sqlType.getKey(), sqlType.getValue());
            }
        } catch (IOException e) {
            log.error(e + "");
            throw new SqlNotFoundException();
        }
    }
}

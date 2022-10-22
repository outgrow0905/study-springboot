package com.springboot.study.ch7.v14;

import com.springboot.study.ch7.v14.jaxb.SqlMap;
import com.springboot.study.ch7.v14.jaxb.SqlType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JaxbReader implements SqlReader {

    private Map<String, String> sqlMap = new HashMap<>();

    @Override
    public String getSql(String queryId) {
        return sqlMap.get(queryId);
    }

    @PostConstruct
    public void loadSql() throws SqlNotFoundException {
        String classPath = SqlMap.class.getPackage().getName();

        try {
            JAXBContext context = JAXBContext.newInstance(classPath);

            Unmarshaller unmarshaller = context.createUnmarshaller();
            SqlMap sqlMap = (SqlMap) unmarshaller.unmarshal(new File("./src/main/resources/ch7/user.xml"));
            for (SqlType sqlType : sqlMap.getSql()) {
                this.sqlMap.put(sqlType.getKey(), sqlType.getValue());
            }
        } catch (JAXBException e) {
            log.error(e + "");
            throw new SqlNotFoundException();
        }
    }
}

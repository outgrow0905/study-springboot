package com.springboot.study.ch7.v17;

import com.springboot.study.ch7.v14.SqlNotFoundException;
import com.springboot.study.ch7.v14.jaxb.SqlMap;
import com.springboot.study.ch7.v14.jaxb.SqlType;
import com.springboot.study.ch7.v15.SqlLoader;
import com.springboot.study.ch7.v15.SqlRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.oxm.Unmarshaller;

import javax.xml.transform.stream.StreamSource;
import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
public class OxmSqlLoaderV2 implements SqlLoader {
    private static final Resource DEFAULT_RESOURCE = new ClassPathResource("ch7/user.xml");
    private Resource resource = DEFAULT_RESOURCE;

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    private Unmarshaller unmarshaller;

    public void setUnmarshaller(Unmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }

    public void loadSql(SqlRegistry sqlRegistry) {
        try {
            SqlMap sqlMap = (SqlMap) unmarshaller.unmarshal(new StreamSource(resource.getInputStream()));
            for (SqlType sqlType : sqlMap.getSql()) {
                sqlRegistry.saveSql(sqlType.getKey(), sqlType.getValue());
            }
        } catch (IOException e) {
            log.error(e + "");
            throw new SqlNotFoundException();
        }
    }
}

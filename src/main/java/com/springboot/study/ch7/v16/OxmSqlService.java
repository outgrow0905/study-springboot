package com.springboot.study.ch7.v16;

import com.springboot.study.ch7.v14.SqlNotFoundException;
import com.springboot.study.ch7.v14.SqlReader;
import com.springboot.study.ch7.v15.SqlLoader;
import com.springboot.study.ch7.v15.SqlRegistry;
import com.springboot.study.ch7.v15.SqlService;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Service;

@Slf4j
public class OxmSqlService implements SqlService {


    private SqlLoader sqlLoader;
    private SqlRegistry sqlRegistry;

    public void setSqlLoader(SqlLoader sqlLoader) {
        this.sqlLoader = sqlLoader;
    }

    public void setSqlRegistry(SqlRegistry sqlRegistry) {
        this.sqlRegistry = sqlRegistry;
    }

    @Override
    public String getSql(String queryId) throws SqlNotFoundException {
        return sqlRegistry.getSql(queryId);
    }

    @PostConstruct
    public void loadSql() {
        sqlLoader.loadSql(sqlRegistry);
    }
}

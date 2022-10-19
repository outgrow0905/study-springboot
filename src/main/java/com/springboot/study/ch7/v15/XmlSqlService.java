package com.springboot.study.ch7.v15;

import com.springboot.study.ch7.v14.SqlNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@RequiredArgsConstructor
@Service
public class XmlSqlService implements SqlService {

    private final SqlLoader sqlLoader;
    private final SqlRegistry sqlRegistry;

    @Override
    public String getSql(String queryId) throws SqlNotFoundException {
        return sqlRegistry.getSql(queryId);
    }

    @PostConstruct
    public void loadSql() {
        sqlLoader.loadSql(sqlRegistry);
    }
}

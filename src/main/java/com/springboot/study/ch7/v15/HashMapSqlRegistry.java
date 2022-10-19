package com.springboot.study.ch7.v15;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class HashMapSqlRegistry implements SqlRegistry {

    private Map<String, String> sqlMap = new HashMap<>();

    @Override
    public void saveSql(String queryId, String query) {
        this.sqlMap.put(queryId, query);
    }

    @Override
    public String getSql(String queryId) {
        return this.sqlMap.get(queryId);
    }
}

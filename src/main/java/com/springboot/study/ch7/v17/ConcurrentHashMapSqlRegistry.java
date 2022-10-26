package com.springboot.study.ch7.v17;

import ch.qos.logback.core.util.StringCollectionUtil;
import com.springboot.study.ch7.v14.SqlNotFoundException;
import com.springboot.study.ch7.v15.SqlRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class ConcurrentHashMapSqlRegistry implements UpdatableSqlRegistry {

    private Map<String, String> sqlMap = new ConcurrentHashMap<>();

    @Override
    public void saveSql(String queryId, String query) {
        this.sqlMap.put(queryId, query);
    }

    @Override
    public String getSql(String queryId) {
        return this.sqlMap.get(queryId);
    }

    @Override
    public void updateSql(String queryId, String query) {
        if (!StringUtils.hasText(sqlMap.get(queryId))) {
            throw new SqlNotFoundException();
        }

        this.saveSql(queryId, query);
    }

    @Override
    public void updateSql(Map<String, String> sqlMap) {
        for (String queryId : sqlMap.keySet()) {
            if (!StringUtils.hasText(sqlMap.get(queryId))) {
                throw new SqlNotFoundException();
            }

            this.saveSql(queryId, sqlMap.get(queryId));
        }
    }
}

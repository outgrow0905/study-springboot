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

/**
 * sqlMap 을 인스턴스변수로 사용하기 때문에, bean으로 등록할 경우 sqlMap이 원치않는 방향으로 변경될 수 있다.
 * 서비스별로 각각의 인스턴스를 생성하여 사용하는 방향으로 한다.
 */
@Slf4j
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

            this.updateSql(queryId, sqlMap.get(queryId));
        }
    }

    @Override
    public void deleteAll() {
        sqlMap.clear();
    }
}

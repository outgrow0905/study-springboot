package com.springboot.study.ch7.v17;

import com.springboot.study.ch7.v15.SqlRegistry;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

public interface UpdatableSqlRegistry extends SqlRegistry {
    void updateSql(String queryId, String query);
    void updateSql(Map<String, String> sqlMap);
    void deleteAll();
}
package com.springboot.study.ch7.v15;

import com.springboot.study.ch7.v14.SqlNotFoundException;

public interface SqlRegistry {
    void saveSql(String queryId, String query);
    String getSql(String queryId) throws SqlNotFoundException;
}

package com.springboot.study.ch7.v15;

public interface SqlRegistry {
    void saveSql(String queryId, String query);
    String getSql(String queryId);
}

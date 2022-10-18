package com.springboot.study.ch7.v14;

public interface SqlReader {
    String getSql(String queryId) throws SqlNotFoundException;
}

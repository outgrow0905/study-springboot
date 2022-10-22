package com.springboot.study.ch7.v15;

import com.springboot.study.ch7.v14.SqlNotFoundException;

public interface SqlService {
    String getSql(String queryId) throws SqlNotFoundException;
}
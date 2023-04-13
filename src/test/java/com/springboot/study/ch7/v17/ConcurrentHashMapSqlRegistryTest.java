package com.springboot.study.ch7.v17;

import com.springboot.study.ch7.v14.SqlNotFoundException;
import com.springboot.study.ch7.v18.EmbeddedH2SqlRegistry;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class ConcurrentHashMapSqlRegistryTest extends UpdatableSqlRegistryTest{
    private ConcurrentHashMapSqlRegistry concurrentHashMapSqlRegistry;

    @BeforeEach
    void setUp() {
        concurrentHashMapSqlRegistry = new ConcurrentHashMapSqlRegistry();
        super.setUpdatableSqlRegistry(concurrentHashMapSqlRegistry);
    }

    @Test
    public void update_sql() {
        super.update_sql();
    }
}
package com.springboot.study.ch7.v17;

import com.springboot.study.ch7.v14.SqlNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class ConcurrentHashMapSqlRegistryTest {

    private ConcurrentHashMapSqlRegistry concurrentHashMapSqlRegistry =
            new ConcurrentHashMapSqlRegistry();

    @Test
    void update_sql() {
        concurrentHashMapSqlRegistry.saveSql("queryId1", "query1");
        concurrentHashMapSqlRegistry.saveSql("queryId2", "query2");
        concurrentHashMapSqlRegistry.saveSql("queryId3", "query3");

        assertEquals("query1", concurrentHashMapSqlRegistry.getSql("queryId1"));
        assertEquals("query2", concurrentHashMapSqlRegistry.getSql("queryId2"));
        assertEquals("query3", concurrentHashMapSqlRegistry.getSql("queryId3"));
        assertThrows(SqlNotFoundException.class,
                () -> concurrentHashMapSqlRegistry.updateSql("queryId4", "query4"));

        concurrentHashMapSqlRegistry.updateSql("queryId1", "newQuery1");
        assertEquals("newQuery1", concurrentHashMapSqlRegistry.getSql("queryId1"));
    }
}
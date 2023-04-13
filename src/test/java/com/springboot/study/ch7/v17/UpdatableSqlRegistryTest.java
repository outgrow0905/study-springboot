package com.springboot.study.ch7.v17;

import com.springboot.study.ch7.v14.SqlNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
public class UpdatableSqlRegistryTest {
    private UpdatableSqlRegistry updatableSqlRegistry;

    public void setUpdatableSqlRegistry(UpdatableSqlRegistry updatableSqlRegistry) {
        this.updatableSqlRegistry = updatableSqlRegistry;
    }

    public void update_sql() {
        updatableSqlRegistry.saveSql("queryId1", "query1");
        updatableSqlRegistry.saveSql("queryId2", "query2");
        updatableSqlRegistry.saveSql("queryId3", "query3");

        assertEquals("query1", updatableSqlRegistry.getSql("queryId1"));
        assertEquals("query2", updatableSqlRegistry.getSql("queryId2"));
        assertEquals("query3", updatableSqlRegistry.getSql("queryId3"));
        assertThrows(SqlNotFoundException.class,
                () -> updatableSqlRegistry.updateSql("queryId4", "query4"));

        updatableSqlRegistry.updateSql("queryId1", "newQuery1");
        assertEquals("newQuery1", updatableSqlRegistry.getSql("queryId1"));
    }
}

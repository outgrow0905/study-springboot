package com.springboot.study.ch7.v18;

import com.springboot.study.ch7.v14.SqlNotFoundException;
import com.springboot.study.ch7.v17.UpdatableSqlRegistry;
import com.springboot.study.ch7.v17.UpdatableSqlRegistryTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
@SpringBootTest
class EmbeddedH2SqlRegistryTest extends UpdatableSqlRegistryTest {

    @Autowired
    private UpdatableSqlRegistry embeddedH2SqlRegistry;

    @Test
    public void update_sql() {
        embeddedH2SqlRegistry.deleteAll();
        super.setUpdatableSqlRegistry(embeddedH2SqlRegistry);
        super.update_sql();
    }

    @Test
    void update_sqlmap() {
        embeddedH2SqlRegistry.deleteAll();
        embeddedH2SqlRegistry.saveSql("queryId1", "query1");
        embeddedH2SqlRegistry.saveSql("queryId2", "query2");
        embeddedH2SqlRegistry.saveSql("queryId3", "query3");

        Map<String, String> sqlMap = Map.of(
                "queryId1", "newQuery1",
                "queryId2", "newQuery2",
                "queryId3", "newQuery3",
                "errorQueryId1", "errorQuery1");
        assertThrows(SqlNotFoundException.class, () -> embeddedH2SqlRegistry.updateSql(sqlMap));
        assertEquals("query1", embeddedH2SqlRegistry.getSql("queryId1"));
        assertEquals("query2", embeddedH2SqlRegistry.getSql("queryId2"));
        assertEquals("query3", embeddedH2SqlRegistry.getSql("queryId3"));
    }
}
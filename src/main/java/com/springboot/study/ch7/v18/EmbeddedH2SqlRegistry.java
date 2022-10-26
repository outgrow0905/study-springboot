package com.springboot.study.ch7.v18;

import com.springboot.study.ch7.v14.SqlNotFoundException;
import com.springboot.study.ch7.v17.UpdatableSqlRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * map을 사용하지 않고 DB를 사용하기 때문에 bean으로 등록할 수 있다.
 * 여러 Service에서 접근하더라도 safe하다.
  */
@Slf4j
@Component
@RequiredArgsConstructor
public class EmbeddedH2SqlRegistry implements UpdatableSqlRegistry {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void saveSql(String queryId, String query) {
        jdbcTemplate.update(
                "INSERT INTO sqlmap (query_id, \"query\") VALUES (?, ?)",
                queryId, query);
    }

    @Override
    public String getSql(String queryId) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT \"query\" FROM sqlmap WHERE query_id = ?",
                    (resultSet, rowNum) -> resultSet.getString(1), queryId);
        } catch (EmptyResultDataAccessException e) {
            log.error(e.getMessage());
            throw new SqlNotFoundException();
        }
    }

    @Override
    public void updateSql(String queryId, String query) {
        if (!StringUtils.hasText(getSql(queryId))) {
            throw new SqlNotFoundException();
        }
        jdbcTemplate.update(
                "UPDATE sqlmap SET \"query\" = ? WHERE query_id = ?",
                query, queryId);
    }

    @Override
    @Transactional
    public void updateSql(Map<String, String> sqlMap) {
        for (String queryId : sqlMap.keySet()) {
            if (!StringUtils.hasText(getSql(queryId))) {
                throw new SqlNotFoundException();
            }

            this.updateSql(queryId, sqlMap.get(queryId));
        }
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM sqlmap");
    }
}

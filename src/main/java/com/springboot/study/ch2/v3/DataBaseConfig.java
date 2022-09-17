package com.springboot.study.ch2.v3;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DataBaseConfig {
    @Autowired
    private DataSource dataSource;

    @Bean
    public JdbcTemplate myJdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }
}

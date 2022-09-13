package com.springboot.study.ch2.v2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StrategyStatement {
    PreparedStatement createPreparedStatement(Connection connection) throws SQLException;
}

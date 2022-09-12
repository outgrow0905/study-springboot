package com.springboot.study.v2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@RequiredArgsConstructor
@Component
public class JdbcContext {

    private final DataSource dataSource;

    public String select(StrategyStatement strategyStatement) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = strategyStatement.createPreparedStatement(connection);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString(2);
            }
        } catch (SQLException e) {
            log.error("select() SQLException");
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {}

            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {}

            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {}

        }
        return Strings.EMPTY;
    }

    public void update(StrategyStatement strategyStatement) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = strategyStatement.createPreparedStatement(connection);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("update() SQLException");
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {}

            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {}
        }
    }
}

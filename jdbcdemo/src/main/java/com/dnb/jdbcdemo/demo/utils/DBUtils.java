package com.dnb.jdbcdemo.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import javax.sql.DataSource;

@Component
public class DBUtils {

    @Autowired
    DataSource dataSource;

    public Optional<Connection> getConnection() {
        try {
            Connection connection = dataSource.getConnection();
            return Optional.of(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

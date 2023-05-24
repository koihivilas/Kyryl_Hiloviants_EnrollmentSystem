package org.application.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private static final HikariConfig config;
    private static final HikariDataSource ds;

    static {
        config = new HikariConfig("src/main/resources/hikari.properties");
        ds = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public static void closePool() {
        ds.close();
    }
}

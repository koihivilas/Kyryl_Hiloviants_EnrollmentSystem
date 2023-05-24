package org.application.connection;

import org.apache.tomcat.jdbc.pool.*;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private static final DataSource ds = new DataSource();

    static {
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/enrollment_system");
        ds.setUsername("root");
        ds.setPassword("root");
        ds.setInitialSize(5);
        ds.setMinIdle(5);
        ds.setMaxIdle(20);
        ds.setMaxActive(100);
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public static void closePool() {
        ds.close();
    }
}

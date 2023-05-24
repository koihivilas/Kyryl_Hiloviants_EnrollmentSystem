package org.application;

import org.application.connection.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        try (Connection conn = ConnectionPool.getConnection()) {

        }
        finally {
            ConnectionPool.closePool();
        }
    }
}

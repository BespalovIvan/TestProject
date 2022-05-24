package com.test.db.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

//Singleton
public final class PostgresConnection {

    private static Connection connection = null;

    private PostgresConnection() {
    }

    public static synchronized Connection getConnection(Properties postgresProperties) throws SQLException {
        if (connection == null) connection = readProperties(postgresProperties);
        return connection;
    }

    private static Connection readProperties(Properties postgresProperties) throws SQLException {
        String url = postgresProperties.getProperty("db.url");
        String user = postgresProperties.getProperty("db.user");
        String password = postgresProperties.getProperty("db.password");
        return DriverManager.getConnection(url, user, password);
    }

}

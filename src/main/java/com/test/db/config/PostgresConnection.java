package com.test.db.config;

import com.test.db.exception.CustomException;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class PostgresConnection {

    private static Connection connection = null;

    private PostgresConnection() {
    }

    public static synchronized Connection getConnection() {
        if (connection == null) {
            connection = createConnection();
        }
        return connection;
    }

    private static Connection createConnection() {
        Properties properties = readProperties();
        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new CustomException("connection not established");
        }
    }

    private static Properties readProperties() {
        Properties props = new Properties();
        Path myPath = Paths.get("src/main/resources/database.properties");
        try {
            BufferedReader bf = Files.newBufferedReader(myPath, StandardCharsets.UTF_8);
            props.load(bf);
        } catch (IOException ex) {
            throw new CustomException("Properties not found");
        }
        return props;
    }
}

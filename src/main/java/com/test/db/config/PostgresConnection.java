package com.test.db.config;

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

//Singleton
public final class PostgresConnection {

    private static Connection connection = null;

    private PostgresConnection() {
    }

    public static synchronized Connection getConnection() throws SQLException {
        if (connection == null) connection = createConnection();
        return connection;
    }

    private static Connection createConnection() throws SQLException {
        Properties properties = readProperties();
        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");
        return DriverManager.getConnection(url, user, password);
    }

    private static Properties readProperties() {
        Properties props = new Properties();
        Path myPath = Paths.get("src/main/resources/database.properties");
        try {
            BufferedReader bf = Files.newBufferedReader(myPath, StandardCharsets.UTF_8);
            props.load(bf);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return props;
    }

}

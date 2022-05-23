package com.test.db;

import java.sql.*;
import java.util.*;

public class Search {
    public static void getCustomerLastname(String lastname) {
        java.util.Properties props = PostgresProperties.readProperties();
        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");
        String query = String.format("SELECT * FROM customers WHERE last_name='%s'", lastname);
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
             Map<String,String> result = new HashMap<>();
                while (resultSet.next()){
                    result.put(resultSet.getString(2), resultSet.getString(3));
            }
                if (result.isEmpty()){
                    System.out.println("Customer not found");
                }
                else {
                    System.out.println(result);
                }

        } catch (SQLException e) {
            System.out.println("connection not established ");
            e.printStackTrace();
        }
    }
}



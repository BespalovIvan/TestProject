package com.test.db.repository;

import com.test.db.config.PostgresProperties;
import com.test.db.config.PostgresConnection;
import com.test.db.domain.Customer;

import java.sql.*;
import java.util.*;

public class DBRepository {

    private final Properties properties;

    public DBRepository() {
        this.properties = PostgresProperties.readProperties();
    }

    public List<Customer> findCustomersFromLastName(String lastname) {
        List<Customer> customers = new ArrayList<>();
        try (
             Statement statement = PostgresConnection.getConnection(properties).createStatement()
        ) {
            String query = String.format("SELECT * FROM customers WHERE UPPER(last_name)='%s'", lastname.toUpperCase());
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                customers.add(new Customer(resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getString(3)));
            }
        } catch (SQLException e) {
            System.out.println("connection not established ");
            e.printStackTrace();
        }

        if (customers.isEmpty()) {
            throw new NoSuchElementException("Customers not found");
        }

        return customers;
    }

    public List<Customer> findCustomersFromProductNameAndMinCount(String product_name,Integer minCount) {
        List<Customer> customers = new ArrayList<>();
        try (
                Statement statement = PostgresConnection.getConnection(properties).createStatement()
        ) {
            String query = String.format("SELECT cus.id, first_name,last_name,product_name,count(*) \n" +
                            "FROM customers cus \n" +
                            "JOIN purchases pur ON (cus.id = pur.customer_id) \n" +
                            "JOIN items it ON (it.id = pur.item_id) WHERE UPPER(product_name) = '%s' \n" +
                            "GROUP BY first_name,last_name,product_name,cus.id \n" +
                            "HAVING count(*)>=%d \n" +
                            "ORDER BY first_name;",
                    product_name.toUpperCase(),minCount);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                customers.add(new Customer(resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getString(3)));
            }
        } catch (SQLException e) {
            System.out.println("connection not established ");
            e.printStackTrace();
        }

        if (customers.isEmpty()) {
            throw new NoSuchElementException("Customers not found");
        }

        return customers;
    }
}

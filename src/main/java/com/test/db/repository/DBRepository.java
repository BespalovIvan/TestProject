package com.test.db.repository;

import com.test.db.config.PostgresProperties;
import com.test.db.config.PostgresConnection;
import com.test.db.domain.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;

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
            String query = String.format("SELECT * FROM customers WHERE last_name='%s'", lastname);
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



    private void findGroupByCustomerNameAndMinCount(){
        String productName;
        String minTimes;
        String query = String.format("SELECT * FROM purchases as p " +
                "join customers as c on p.customers_id=c.id " +
                "and items as i on p.item_id=i.id");
    }

}

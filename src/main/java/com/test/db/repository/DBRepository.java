package com.test.db.repository;

import com.test.db.config.PostgresConnection;
import com.test.db.domain.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class DBRepository {

    public List<Customer> findCustomersFromLastName(String lastname) {
        return executeQueryWithFindCustomers(String.format("SELECT * FROM customers WHERE UPPER(last_name)='%s'",
                lastname.toUpperCase()));
    }

    public List<Customer> findCustomersFromProductNameAndMinCount(String productName, int minCount) {
        String query = String.format("SELECT cus.id, first_name,last_name,product_name,count(*) \n" +
                        "FROM customers cus \n" +
                        "JOIN purchases pur ON (cus.id = pur.customer_id) \n" +
                        "JOIN items it ON (it.id = pur.item_id) WHERE UPPER(product_name) = '%s' \n" +
                        "GROUP BY first_name,last_name,product_name,cus.id \n" +
                        "HAVING count(*)>=%d \n" +
                        "ORDER BY first_name;",
                productName.toUpperCase(), minCount);
        return executeQueryWithFindCustomers(query);
    }

    public List<Customer> findCustomerFromMinAndMaxExpenses(int minExpenses, int maxExpenses) {
        String query = String.format("SELECT  cus.id,first_name,last_name,SUM(price)\n" +
                "FROM customers cus \n" +
                "JOIN purchases pur ON (cus.id = pur.customer_id) \n" +
                "JOIN items it ON (it.id = pur.item_id) \n" +
                "GROUP BY first_name, last_name, cus.id \n" +
                "HAVING SUM(price) BETWEEN %d and %d \n" +
                "ORDER BY first_name;", minExpenses, maxExpenses);
        return executeQueryWithFindCustomers(query);
    }

    private List<Customer> executeQueryWithFindCustomers(String query) {
        List<Customer> customers = new ArrayList<>();
        try (Statement statement = PostgresConnection.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
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

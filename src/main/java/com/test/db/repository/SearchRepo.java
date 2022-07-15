package com.test.db.repository;

import com.test.db.config.PostgresConnection;
import com.test.db.domain.entities.Customer;
import com.test.db.exception.CustomException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SearchRepo {

    public List<Customer> findCustomersFromLastName(String lastname) {
        List<Customer> customers = executeQueryWithFindCustomers(String.format("SELECT * FROM customers WHERE UPPER(last_name)='%s'",
                lastname.toUpperCase()));
        if(customers.isEmpty()){
            throw new CustomException("no customer found by this lastname");
        }
        return customers;
    }

    public List<Customer> findCustomersFromProductNameAndMinCount(String productName, int minCount) {
        String query = String.format("SELECT cus.id, first_name,last_name,product_name \n" +
                "FROM customers cus \n" +
                "JOIN purchases pur ON (cus.id = pur.customer_id) \n" +
                "JOIN items it ON (it.id = pur.item_id) WHERE UPPER(product_name) = '%s' \n" +
                "GROUP BY first_name,last_name,product_name,cus.id \n" +
                "HAVING count(*)>=%d;\n", productName.toUpperCase(), minCount);
        List<Customer> customers = executeQueryWithFindCustomers(query);
        if(customers.isEmpty()){
            throw new CustomException("there were no customers who bought this product so many times");
        }
        return customers;
    }

    public List<Customer> findCustomerFromMinAndMaxExpenses(int minExpenses, int maxExpenses) {
        String query = String.format("SELECT cus.id,first_name,last_name\n" +
                "FROM customers cus \n" +
                "JOIN purchases pur ON (cus.id = pur.customer_id) \n" +
                "JOIN items it ON (it.id = pur.item_id) \n" +
                "GROUP BY first_name, last_name, cus.id \n" +
                "HAVING SUM(price) BETWEEN %d and %d; \n", minExpenses, maxExpenses);
        List<Customer> customers = executeQueryWithFindCustomers(query);
        if(customers.isEmpty()){
            throw new CustomException("there were no customers who bought the goods in this price range");
        }
        return customers;
    }
    public List<Customer> findBadCustomers(int countBadCustomers) {
        String query = String.format("SELECT cus.id, first_name,last_name \n" +
                "FROM customers cus \n" +
                "JOIN purchases pur ON (cus.id = pur.customer_id) \n" +
                "GROUP BY first_name, last_name,cus.id \n" +
                "ORDER BY count(*) \n" +
                "LIMIT %d;", countBadCustomers);
        List<Customer> customers = executeQueryWithFindCustomers(query);
        if(customers.isEmpty()){
            throw new CustomException("no such customers");
        }
        return customers;
    }

    private List<Customer> executeQueryWithFindCustomers(String query) {
        List<Customer> customers = new ArrayList<>();
        try (Statement statement = PostgresConnection.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                customers.add(new Customer(resultSet.getString(3), resultSet.getString(2)));
            }
        } catch (SQLException e) {
            throw new CustomException("connection not established");
        }
        return customers;
    }
}
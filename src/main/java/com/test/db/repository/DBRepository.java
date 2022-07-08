package com.test.db.repository;

import com.test.db.config.PostgresConnection;
import com.test.db.domain.Customer;
import com.test.db.domain.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

public class DBRepository {

    public List<Customer> findCustomersFromLastName(String lastname) {
        return executeQueryWithFindCustomers(String.format("SELECT * FROM customers WHERE UPPER(last_name)='%s'",
                lastname.toUpperCase()));
    }

    public List<Customer> findCustomersFromProductNameAndMinCount(String productName, int minCount) {
        String query = String.format("SELECT cus.id, first_name,last_name,product_name \n" +
                "FROM customers cus \n" +
                "JOIN purchases pur ON (cus.id = pur.customer_id) \n" +
                "JOIN items it ON (it.id = pur.item_id) WHERE UPPER(product_name) = '%s' \n" +
                "GROUP BY first_name,last_name,product_name,cus.id \n" +
                "HAVING count(*)>=%d;\n", productName.toUpperCase(), minCount);
        return executeQueryWithFindCustomers(query);
    }

    public List<Customer> findCustomerFromMinAndMaxExpenses(int minExpenses, int maxExpenses) {
        String query = String.format("SELECT cus.id,first_name,last_name\n" +
                "FROM customers cus \n" +
                "JOIN purchases pur ON (cus.id = pur.customer_id) \n" +
                "JOIN items it ON (it.id = pur.item_id) \n" +
                "GROUP BY first_name, last_name, cus.id \n" +
                "HAVING SUM(price) BETWEEN %d and %d; \n", minExpenses, maxExpenses);
        return executeQueryWithFindCustomers(query);
    }

    public List<Customer> findBadCustomers(int countBadCustomers) {
        String query = String.format("SELECT cus.id, first_name,last_name \n" +
                "FROM customers cus \n" +
                "JOIN purchases pur ON (cus.id = pur.customer_id) \n" +
                "GROUP BY first_name, last_name,cus.id \n" +
                "ORDER BY count(*) \n" +
                "LIMIT %d;", countBadCustomers);
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

        public List<Customer> getStat (String startDate, String endDate) {
        String queryGetCustomers = String.format("select c.id, first_name, last_name,i.id, product_name,SUM(price)\n" +
                "from customers c \n" +
                "JOIN purchases p ON (c.id = p.customer_id) \n" +
                "JOIN items i ON (p.item_id = i.id) \n" +
                "WHERE p.date BETWEEN TO_DATE('%s','YYYY-MM-DD') and TO_DATE('%s','YYYY-MM-DD')\n" +
                "GROUP BY c.id, c.first_name, last_name, product_name,i.id\n" +
                "ORDER BY first_name,last_name,SUM(price) DESC;\n",startDate,endDate);
            Map<Integer,Customer> customers = new HashMap<>();
        try (Statement statement = PostgresConnection.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(queryGetCustomers)) {
            while (resultSet.next()) {
                if(customers.containsKey(resultSet.getInt(1))){
                    customers.get(resultSet.getInt(1)).getPurchases().put(resultSet.getString(5),
                            resultSet.getInt(6));
                }
                else {
                    Map<String,Integer> purchases = new HashMap<>();
                    purchases.put(resultSet.getString(5),resultSet.getInt(6));
                    customers.put(resultSet.getInt(1),new Customer(resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),purchases));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(customers.values());
    }
}

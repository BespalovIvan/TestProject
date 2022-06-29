package com.test.db.repository;

import com.test.db.config.PostgresConnection;
import com.test.db.domain.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

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
                        "HAVING count(*)>=%d;\n" , productName.toUpperCase(), minCount);
        return executeQueryWithFindCustomers(query);
    }

    public List<Customer> findCustomerFromMinAndMaxExpenses(int minExpenses, int maxExpenses) {
        String query = String.format("SELECT cus.id,first_name,last_name\n" +
                "FROM customers cus \n" +
                "JOIN purchases pur ON (cus.id = pur.customer_id) \n" +
                "JOIN items it ON (it.id = pur.item_id) \n" +
                "GROUP BY first_name, last_name, cus.id \n" +
                "HAVING SUM(price) BETWEEN %d and %d; \n" , minExpenses, maxExpenses);
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

    public Map<String,List<Object>> getTotalDays(String startDate, String endDate) {
        String query = String.format("SELECT sum((extract(dow from d) between 1 and 5)::int ) month_working_days_till_date \n" +
                "FROM generate_series(date_trunc('day', TO_DATE('%s','YYYY-MM-DD')) , TO_DATE('%s','YYYY-MM-DD'), interval '1 day') x(d);",startDate,endDate);
        Map<String,List<Object>> result = new HashMap<>();
        List<Object> values = new ArrayList<>();
        try (Statement statement = PostgresConnection.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                values.add(resultSet.getInt(1));
                result.put("TotalDays", values);
            }
        }catch (SQLException e){
            System.out.println("connection not established ");
            e.printStackTrace();
        }
        return result;
    }

}

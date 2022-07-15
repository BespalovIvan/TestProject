package com.test.db.repository;

import com.test.db.config.PostgresConnection;
import com.test.db.domain.dto.StatResultDTO;
import com.test.db.domain.entities.CustomerForStat;
import com.test.db.domain.entities.Item;
import com.test.db.exception.CustomException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatRepo {
    public StatResultDTO getStat(String startDate, String endDate) {
        String queryGetCustomers = String.format("select c.id, first_name, last_name,i.id, " +
                "product_name,SUM(price), \n" +
                "(SELECT sum((extract(dow from d) between 1 and 5)::int ) total_days\n" +
                "FROM generate_series(date_trunc('day', TO_DATE('%s','YYYY-MM-DD')), " +
                "TO_DATE('%s','YYYY-MM-DD'), interval '1 day') x(d))\n" +
                "from customers c \n" +
                "JOIN purchases p ON (c.id = p.customer_id) \n" +
                "JOIN items i ON (p.item_id = i.id) \n" +
                "WHERE p.date BETWEEN TO_DATE('%s','YYYY-MM-DD') and TO_DATE('%s','YYYY-MM-DD') " +
                "and extract(dow from p.date) != 5 and  extract(dow from p.date) != 6\n" +
                "GROUP BY c.id, c.first_name, last_name,product_name,i.id;", startDate, endDate, startDate, endDate);
        return executeQueryWithGetStat(queryGetCustomers);
    }

    private StatResultDTO executeQueryWithGetStat(String query) {
        Map<Integer, CustomerForStat> customers = new HashMap<>();
        int totalDays = 0;
        int totalExpenses = 0;
        double avgExpenses = 0.0;
        try (Statement statement = PostgresConnection.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                if (customers.containsKey(resultSet.getInt(1))) {
                    customers.get(resultSet.getInt(1))
                            .getPurchases()
                            .add(new Item(resultSet.getString(5), resultSet.getInt(6)));
                    int total = customers.get(resultSet.getInt(1)).getTotalExpenses();
                    customers.get(resultSet.getInt(1))
                            .setTotalExpenses(total + (resultSet.getInt(6)));
                } else {
                    List<Item> purchases = new ArrayList<>();
                    purchases.add(new Item(resultSet.getString(5),
                            resultSet.getInt(6)));
                    customers.put(resultSet.getInt(1),
                            new CustomerForStat(resultSet.getString(3)
                                    + " " + resultSet.getString(2),
                                    purchases, resultSet.getInt(6)));
                }
                totalDays = resultSet.getInt(7);
            }
        } catch (SQLException e) {
            throw new CustomException("wrong date format");
        }
        for (CustomerForStat c : customers.values()) {
            totalExpenses = totalExpenses + c.getTotalExpenses();
            avgExpenses = (double) totalExpenses / customers.values().size();
            BigDecimal bd = new BigDecimal(Double.toString(avgExpenses));
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            avgExpenses = bd.doubleValue();
        }
        List<CustomerForStat> customerForStatList = new ArrayList<>(customers.values());
        customerForStatList.sort((c1, c2) -> c2.getTotalExpenses() - c1.getTotalExpenses());
        customerForStatList.forEach(customerForStat -> customerForStat.getPurchases()
                .sort((p1, p2) -> p2.getExpenses() - p1.getExpenses()));
        if (customerForStatList.isEmpty()) {
            throw new CustomException("there were no customers who bought goods on these dates");
        }
        return new StatResultDTO("stat", totalDays, customerForStatList, totalExpenses, avgExpenses);
    }
}

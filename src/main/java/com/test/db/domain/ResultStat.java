package com.test.db.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ResultStat {
    private Integer totalDays;
    private List<Customer> customers;
    private double totalExpenses;
    private double avgExpenses ;

}

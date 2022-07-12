package com.test.db.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
public class ResultStat {
    private List<CustomerForStat> customers;
    private Integer totalDays;
    private Integer totalExpenses;
    private int avgExpenses ;

}

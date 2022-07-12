package com.test.db.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CustomerForStat {
    private String firstname;
    private List<Item> purchases;
    private Integer totalExpenses;
}

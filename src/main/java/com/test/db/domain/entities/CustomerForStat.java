package com.test.db.domain.entities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonPropertyOrder({"name", "purchases", "totalExpenses"})
@Getter
@Setter
@AllArgsConstructor
public class CustomerForStat {

    private String name;
    private List<Item> purchases;
    private int totalExpenses;

}

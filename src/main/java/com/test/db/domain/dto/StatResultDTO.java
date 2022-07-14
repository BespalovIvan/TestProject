package com.test.db.domain.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.test.db.domain.entities.CustomerForStat;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonPropertyOrder({"type", "totalDays", "customers", "totalExpenses", "avgExpenses"})
@Getter
@Setter
public class StatResultDTO extends ResultDTO {

    private Integer totalDays;
    private List<CustomerForStat> customers;
    private int totalExpenses;
    private Double avgExpenses;

    public StatResultDTO(String type) {
        super(type);
    }

    public StatResultDTO(String type, Integer totalDays, List<CustomerForStat> customers, int totalExpenses, Double avgExpenses) {
        super(type);
        this.totalDays = totalDays;
        this.customers = customers;
        this.totalExpenses = totalExpenses;
        this.avgExpenses = avgExpenses;
    }
}

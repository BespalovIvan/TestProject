package com.test.db.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class CustomerForStat {
    private Integer id;
    private String name;
    private Map<String,Integer> purchases;

}

package com.test.db.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class ResultSearch {

    private Map<String,String> criteria;
    private List<Customer> results;

}

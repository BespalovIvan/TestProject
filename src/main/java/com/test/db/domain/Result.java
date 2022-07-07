package com.test.db.domain;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class Result {

    private Criteria criteria;
    private List<Customer> list;

}

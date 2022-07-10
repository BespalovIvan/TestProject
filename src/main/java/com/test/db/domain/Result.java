package com.test.db.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class Result {

    private Map<Criteria, List<Customer>> results;

    @Override
    public String toString() {
        return results.toString();
    }
}

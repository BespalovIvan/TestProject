package com.test.db.domain.results;

import com.test.db.domain.entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class ResultSearch {

    private Map<String,String> criteria;
    private List<Customer> results;

}

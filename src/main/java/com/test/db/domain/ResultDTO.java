package com.test.db.domain;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class ResultDTO {

    protected final Map<String, List<Customer>> searchResult = new HashMap<>();
    protected String type;
    protected List<Result> results;
}

package com.test.db.domain;

import java.util.HashMap;
import java.util.Map;

public class Criteria {

    private final Map<String, String> criteria;

    public Criteria(String key, String value) {
        this.criteria = new HashMap<>();
        criteria.put(key, value);

    }

    public Criteria(String key1, String value1, String key2, String value2) {
        this.criteria = new HashMap<>();
        criteria.put(key1, value1);
        criteria.put(key2, value2);

    }

}

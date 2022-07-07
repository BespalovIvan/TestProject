package com.test.db.domain;

import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
class Criteria {
    private Map<String, String> criteria = new HashMap<>();
}

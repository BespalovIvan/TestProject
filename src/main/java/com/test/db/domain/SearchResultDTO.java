package com.test.db.domain;

import java.util.List;
import java.util.Map;

public class SearchResultDTO extends ResultDTO {
    //для search определяем какие-то такие параметры
    private final Map<String, List<Customer>> result;

    public SearchResultDTO(String type, Map<String, List<Customer>> result) {
        super(type);
        this.result = result;
    }

    @Override
    public String toString() {
        return "SearchResultDTO{" +
                "type='" + type + '\'' +
                ", result=" + result +
                '}';
    }

}

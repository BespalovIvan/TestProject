package com.test.db.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchResultDTO extends ResultDTO {
    //для search определяем какие-то такие параметры
    //private final Map<String, List<Customer>> result;

    @Getter
    private List<Result> result;

    public SearchResultDTO(String type) {
        super(type);
        this.result = new ArrayList<>();
    }

}

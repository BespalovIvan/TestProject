package com.test.db.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class SearchResultDTO extends ResultDTO {

    @Getter
    private List<ResultSearch> resultSearch;

    public SearchResultDTO(String type) {
        super(type);
        this.resultSearch = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "type:" + type + ", "  + "results: " + resultSearch;
    }
}

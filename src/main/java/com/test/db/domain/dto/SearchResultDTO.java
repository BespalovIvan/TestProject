package com.test.db.domain.dto;

import com.test.db.domain.results.ResultSearch;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class SearchResultDTO extends ResultDTO {

    @Getter
    private final List<ResultSearch> results;

    public SearchResultDTO(String type) {
        super(type);
        this.results = new ArrayList<>();
    }
}

package com.test.db.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class SearchResultDTO extends ResultDTO {

    @Getter
    private List<ResultSearch> result;

    public SearchResultDTO(String type) {
        super(type);
        this.result = new ArrayList<>();
    }
}

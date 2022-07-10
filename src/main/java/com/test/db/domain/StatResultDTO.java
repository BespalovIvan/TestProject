package com.test.db.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class StatResultDTO extends ResultDTO {

    @Getter
    private List<ResultStat> resultStats;

    public StatResultDTO(String type) {
        super(type);
        this.resultStats = new ArrayList<>();
    }
}

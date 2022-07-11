package com.test.db.domain;

import lombok.Getter;
import lombok.Setter;

public class StatResultDTO extends ResultDTO {

    @Getter
    @Setter
    private ResultStat resultStats;

    public StatResultDTO(String type) {
        super(type);
    }
}

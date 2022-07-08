package com.test.db.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class StatResultDTO extends ResultDTO {
    //для Stat определяем какие-то другие

    @Getter
    private List<Result> result;

    public StatResultDTO(String type) {
        super(type);
        this.result = new ArrayList<>();
    }


    @Override
    public String toString() {
        return "type:" + type + ", "  + "results: " + result;
    }

}

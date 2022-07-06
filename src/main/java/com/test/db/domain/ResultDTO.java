package com.test.db.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultDTO {

    private final Map<String, List<Customer>> searchResult = new HashMap<>();
    //другие обьекты для статистики. Заполняем то что нужно в зависимости от типа запроса на входе

}

package com.test.db.factory;

import com.test.db.repository.SearchRepo;
import com.test.db.repository.StatRepo;
import com.test.db.service.CustomerService;
import com.test.db.service.FileService;
import com.test.db.service.impl.CustomerSearch;
import com.test.db.service.impl.CustomerStat;

public class SearchFactory {

    public static CustomerService createCustomerService(String type, FileService fileService) {
        switch (type) {
            case "search":
                return new CustomerSearch(fileService, new SearchRepo());
            case "stat":
                return new CustomerStat(fileService, new StatRepo());
            default:
                throw new IllegalArgumentException("invalid search type: " + type);
        }
    }
}

package com.test.db;

import com.test.db.repository.DBRepository;
import com.test.db.service.impl.CustomerSearch;
import com.test.db.service.impl.CustomerStat;
import com.test.db.service.impl.FileServiceImpl;

public class ServiceFactory {
    public static void main(String[] args) {
        switch (args[0]) {
            case "search":
                new CustomerSearch(new FileServiceImpl(args[1], args[2]), new DBRepository()).start();
                break;
            case "stat":
                new CustomerStat(new FileServiceImpl(args[1], args[2]),new DBRepository()).start();
                break;
        }
    }
}
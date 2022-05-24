package com.test.db;

import com.test.db.repository.DBRepository;
import com.test.db.service.impl.CustomerServiceImpl;
import com.test.db.service.impl.FileServiceImpl;

public class Main {
    public static void main(String[] args) throws Exception {
        switch (args[0]) {
            case "search":
                new CustomerServiceImpl(new FileServiceImpl(args[1], args[2]), new DBRepository()).start();
                break;
            case "stat":
                System.out.println("2");
                break;
        }
    }
}
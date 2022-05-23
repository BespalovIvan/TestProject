package com.test.db;

import com.test.db.service.impl.CustomerServiceImpl;

public class Main {
    public static void main(String[] args) throws Exception {
        switch (args[0]) {
            case "search":
                new CustomerServiceImpl().start(args[1], args[2]);
                break;
            case "stat":
                System.out.println("2");
                break;
        }
    }
}
package com.test.db;

import com.test.db.service.impl.FileServiceImpl;

public class Main {

    public static void main(String[] args) {
        SearchFactory.createCustomerService(args[0], new FileServiceImpl(args[1], args[2])).start();
    }

}
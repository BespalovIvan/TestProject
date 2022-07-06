package com.test.db;

import com.test.db.service.impl.FileServiceImpl;

public class Main {

    public static void main(String[] args) {
        if (args.length < 3) throw new IllegalArgumentException("Количество аргументов не может быть меньше 3-х!");
        SearchFactory.createCustomerService(args[0], new FileServiceImpl(args[1], args[2])).start();
    }
}
package com.test.db;

import com.test.db.repository.DBRepository;
import com.test.db.service.CustomerService;
import com.test.db.service.FileService;
import com.test.db.service.impl.CustomerSearch;
import com.test.db.service.impl.CustomerStat;

/*Factory Method
Пример одной из реализации фабричного метода, который мы вынесли из абстрактного класса CustomerService
*/
public class SearchFactory {

    public static CustomerService createCustomerService(String type, FileService fileService) {
        switch (type) {
            case "search":
                return new CustomerSearch(fileService, new DBRepository());
            case "stat":
                return new CustomerStat(fileService, new DBRepository());
            default:
                throw new IllegalArgumentException("Неверный тип поиска: " + type);
        }
    }

}

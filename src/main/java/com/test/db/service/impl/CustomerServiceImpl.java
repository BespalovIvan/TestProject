package com.test.db.service.impl;

import com.test.db.domain.Customer;
import com.test.db.repository.DBRepository;
import com.test.db.service.CustomerService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerServiceImpl implements CustomerService {

    @Override
    public void start(String inputFile, String outputFile)  {
        // определяем какую операцию(метод) вызвать из нужных
        //JSONObject object = (JSONObject) new FileServiceImpl().readFile(inputFile);
        //<lastName, List<Ivanov>>
        //<minTimes, Map<productName, minTimes>>
        //<price, Map<1, [min, max]]>>
        //if()



    }

    public static Map<String, Object> findCustomers(String lastName) {
        List<Customer> customersFromLastName = new DBRepository().findCustomersFromLastName(lastName);
        Map<String, Object> response = new HashMap<>();
        return response;
    }

}

package com.test.db.service.impl;

import com.test.db.CustomException;
import com.test.db.domain.Customer;
import com.test.db.repository.DBRepository;
import com.test.db.service.CustomerService;
import com.test.db.service.FileService;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerServiceImpl implements CustomerService {

    private final FileService fileService;
    private final DBRepository dbRepository;
    private final Map<String, List<Customer>> result = new HashMap<>();

    public CustomerServiceImpl(FileService fileService, DBRepository dbRepository) {
        this.fileService = fileService;
        this.dbRepository = dbRepository;
    }

    /*
    TODO
     1) Тут отлично может подойти паттерн фабричный метод, в будущем попробуй переписать.
     2) Если ключи начинают повторяться, целесообразно их хранить в константах, ENUM к примеру
    */
    @Override
    public void start() throws CustomException {
        JSONObject fileObject = fileService.readFile();
        JSONArray criteria = fileObject.optJSONArray("criteria");
        if (criteria == null) throw new CustomException("Критерий criteria не был найден в json.");

        for (int i = 0; i < criteria.length(); i++) {
            if (!criteria.getJSONObject(i).optString("lastName").equals("")) {
                findCustomers(criteria.getJSONObject(i).optString("lastName"));
            } else if (!criteria.getJSONObject(i).optString("productName").equals("")) {
                findCustomersFromProduct(criteria.getJSONObject(i).optString("productName"),
                        Integer.valueOf(criteria.getJSONObject(i).optString("minTimes")));
            } else if (!criteria.getJSONObject(i).optString("minExpenses").equals("")) {
                findCustomersFromExpenses(Integer.parseInt(criteria.getJSONObject(i).optString("minExpenses")),
                        Integer.parseInt(criteria.getJSONObject(i).optString("maxExpenses")));
            }
        }

        fileService.writeFile(result);
    }

    private void findCustomers(String lastName) {
        List<Customer> customers = dbRepository.findCustomersFromLastName(lastName);
        if (!customers.isEmpty()) checkExistAndAdd("lastName", customers);

    }

    private void findCustomersFromProduct(String productName, Integer minCount) {
        List<Customer> customers = dbRepository.findCustomersFromProductNameAndMinCount(productName, minCount);
        if (!customers.isEmpty()) checkExistAndAdd("productName", customers);
    }

    private void findCustomersFromExpenses(int minExpenses, int maxExpenses) {
        List<Customer> customers = dbRepository.findCustomerFromMinAndMaxExpenses(minExpenses, maxExpenses);
        if (!customers.isEmpty()) checkExistAndAdd("expenses", customers);
    }

    private void checkExistAndAdd(String key, List<Customer> customers) {
        if (result.containsKey(key)) {
            result.get(key).addAll(customers);
        } else {
            result.put(key, customers);
        }
    }

}

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
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService {

    private final FileService fileService;
    private final DBRepository dbRepository;

    public CustomerServiceImpl(FileService fileService, DBRepository dbRepository) {
        this.fileService = fileService;
        this.dbRepository = dbRepository;
    }

    @Override
    public void start() throws CustomException {
        JSONObject fileObject = fileService.readFile();
        JSONArray criteria = fileObject.optJSONArray("criteria");
        if (criteria == null) throw new CustomException("Критерий criteria не был найден в json.");
        Map<Integer, Object> result = new HashMap<>();
        for (int i = 0; i < criteria.length(); i++) {
            if (!criteria.getJSONObject(i).optString("lastName").equals("")) {
                result.putAll(findCustomers(criteria.getJSONObject(i).optString("lastName")));
            } else if (!criteria.getJSONObject(i).optString("productName").equals("")) {
                result.putAll(findCustomersFromProduct(criteria.getJSONObject(i).optString("productName"), Integer.valueOf(criteria.getJSONObject(i).optString("minTimes"))));
            }
        }

        fileService.writeFile(result);
    }

    private Map<Integer, Object> findCustomers(String lastName) {
        return dbRepository.findCustomersFromLastName(lastName).stream()
                .collect(Collectors.toMap(Customer::getId, Customer::toString));
    }

    private Map<Integer, Object> findCustomersFromProduct(String productName, Integer minCount) {
        List<Customer> customersFromProductName = dbRepository.findCustomersFromProductNameAndMinCount(productName, minCount);
        Map<Integer, Object> response = new HashMap<>();
        for (Customer c : customersFromProductName) {
            response.put(c.getId(), c.toString());
        }
        return response;
    }

}

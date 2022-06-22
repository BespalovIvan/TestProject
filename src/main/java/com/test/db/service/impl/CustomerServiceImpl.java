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

    public CustomerServiceImpl(FileService fileService, DBRepository dbRepository) {
        this.fileService = fileService;
        this.dbRepository = dbRepository;
    }

    @Override
    public void start() throws CustomException {
        JSONObject fileObject = fileService.readFile();
        JSONArray criteria = fileObject.optJSONArray("criteria");
        if (criteria == null) throw new CustomException("Критерий criteria не был найден в json.");
        Map<String, List<Customer>> result = new HashMap<>();
        for (int i = 0; i < criteria.length(); i++) {
            if (!criteria.getJSONObject(i).optString("lastName").equals("")) {
                result.putAll(findCustomers("lastName",criteria.getJSONObject(i).optString("lastName")));
            } else if (!criteria.getJSONObject(i).optString("productName").equals("")) {
                result.putAll(findCustomersFromProduct("productName", criteria.getJSONObject(i).optString("productName"),
                        Integer.valueOf(criteria.getJSONObject(i).optString("minTimes"))));
            }

        }
        fileService.writeFile(result);
    }

    private Map<String, List<Customer>> findCustomers(String criteria,String lastName) {
        List<Customer> customersFromLastname = dbRepository.findCustomersFromLastName(lastName);
        Map<String,List<Customer>> response = new HashMap<>();
        response.put(criteria,customersFromLastname);
        return response;

    }

    private Map<String,List<Customer>> findCustomersFromProduct(String criteria,String productName, Integer minCount) {
        Map<String,List<Customer>> response = new HashMap<>();
        List<Customer> customersFromProductName = dbRepository.findCustomersFromProductNameAndMinCount(productName,minCount);
        response.put(criteria, customersFromProductName);
        return response;
    }
}

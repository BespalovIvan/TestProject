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
                result.put("lastName",findCustomers(criteria.getJSONObject(i).optString("lastName")));
            } else if (!criteria.getJSONObject(i).optString("productName").equals("")) {
                result.put("productName",findCustomersFromProduct( criteria.getJSONObject(i).optString("productName"),
                        Integer.valueOf(criteria.getJSONObject(i).optString("minTimes"))));
            }
            else if (!criteria.getJSONObject(i).optString("minExpenses").equals("")) {
                result.put("Expenses",findCustomersFromExpenses(Integer.parseInt(criteria.getJSONObject(i).optString("minExpenses")),
                        Integer.parseInt(criteria.getJSONObject(i).optString("maxExpenses"))));
            }
        }
        fileService.writeFile(result);
    }

    private List<Customer> findCustomers(String lastName) {
        return dbRepository.findCustomersFromLastName(lastName);

    }
    private List<Customer> findCustomersFromProduct(String productName, Integer minCount) {
        return dbRepository.findCustomersFromProductNameAndMinCount(productName,minCount);
    }
    private List<Customer> findCustomersFromExpenses(int minExpenses, int maxExpenses){
        return dbRepository.findCustomerFromMinAndMaxExpenses(minExpenses,maxExpenses);
    }
}

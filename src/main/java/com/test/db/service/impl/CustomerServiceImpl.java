package com.test.db.service.impl;

import com.test.db.CustomException;
import com.test.db.domain.Customer;
import com.test.db.repository.DBRepository;
import com.test.db.service.CustomerService;
import com.test.db.service.FileService;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
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
    public void start() throws CustomException, IOException {
        JSONObject fileObject = fileService.readFile();
        JSONArray criteria = fileObject.optJSONArray("criteria");
        if (criteria == null) throw new CustomException("Критерий criteria не был найден в json.");
        for (int i = 0; i < criteria.length(); i++) {
            if (!criteria.getJSONObject(i).optString("lastName").equals("")) {
                Map<Integer, Object> customers = findCustomers(criteria.getJSONObject(i).optString("lastName"));
                fileService.writeFile(customers);
            }
        }
    }

    public Map<Integer, Object> findCustomers(String lastName) {
        List<Customer> customersFromLastName = dbRepository.findCustomersFromLastName(lastName);
        Map<Integer, Object> response = new HashMap<>();
        for (Customer c : customersFromLastName) {
            response.put(c.getId(), c.toString());
        }
        return response;
    }

}

package com.test.db.service.impl;

import com.test.db.CustomException;
import com.test.db.domain.Customer;
import com.test.db.repository.DBRepository;
import com.test.db.service.CriteriaFactory;
import com.test.db.service.CustomerService;
import com.test.db.service.FileService;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerSearch implements CustomerService {

    private final FileService fileService;
    private final DBRepository dbRepository;
    private final Map<String, List<Customer>> result = new HashMap<>();

    public CustomerSearch(FileService fileService, DBRepository dbRepository) {
        this.fileService = fileService;
        this.dbRepository = dbRepository;
    }

    public void start() throws CustomException {
        JSONObject fileObject = fileService.readFile();
        JSONArray criteria = fileObject.optJSONArray("criteria");
        if (criteria == null) throw new CustomException("Критерий criteria не был найден в json.");

        for (int i = 0; i < criteria.length(); i++) {
            checkExistAndAdd(criteria.getJSONObject(i).toString(),
                    CriteriaFactory.createCustomersFindService(i, criteria, dbRepository)
                            .find());
        }

        fileService.writeFile(result);
    }

    private void checkExistAndAdd(String key, List<Customer> customers) {
        if (result.containsKey(key)) {
            result.get(key).addAll(customers);
        } else {
            result.put(key, customers);
        }
    }
}

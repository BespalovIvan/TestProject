package com.test.db.service;

import com.test.db.exception.CustomException;
import com.test.db.domain.Customer;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Template
public abstract class CustomerService {

    private final FileService fileService;
    private final Map<String, List<Customer>> result = new HashMap<>();

    protected CustomerService(FileService fileService) {
        this.fileService = fileService;
    }

    public abstract void readJSONAndFind(JSONObject fileObject) throws CustomException;

    public void start() throws CustomException {
        readJSONAndFind(fileService.readFile());
        fileService.writeFile(result);
    }

    protected void checkExistAndAdd(String key, List<Customer> customers) {
        if (result.containsKey(key)) {
            result.get(key).addAll(customers);
        } else {
            result.put(key, customers);
        }
    }

}

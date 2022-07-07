package com.test.db.service;

import com.test.db.domain.Customer;
import com.test.db.domain.ResultDTO;
import com.test.db.exception.CustomException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Template
public abstract class CustomerService {

    private final FileService fileService;
    protected final Map<String, List<Customer>> result = new HashMap<>();

    protected CustomerService(FileService fileService) {
        this.fileService = fileService;
    }

    public abstract void readJSONAndFind(JSONObject fileObject) throws CustomException;

    public abstract ResultDTO getResult();

    public void start() throws CustomException {
        readJSONAndFind(fileService.readFile());
        fileService.writeFile(getResult().toString());
    }

    protected void checkExistAndAdd(String key, List<Customer> customers) {
        if (result.containsKey(key)) {
            result.get(key).addAll(customers);
        } else {
            result.put(key, customers);
        }
    }

}

package com.test.db.service;

import com.test.db.domain.Customer;
import com.test.db.exception.CustomException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public interface FileService {

    JSONObject readFile() throws CustomException;

    void writeFile(Map<String, List<Customer>> result);

}

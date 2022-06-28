package com.test.db.service.impl;

import com.test.db.CustomException;
import com.test.db.domain.Customer;
import com.test.db.repository.DBRepository;
import com.test.db.service.CustomerService;
import com.test.db.service.FileService;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerStat  implements CustomerService {

    private final FileService fileService;
    private final DBRepository dbRepository;
    private final Map<String, List<Customer>> result = new HashMap<>();

    public CustomerStat(FileService fileService, DBRepository dbRepository) {
        this.fileService = fileService;
        this.dbRepository = dbRepository;
    }

    @Override
    public void start() throws CustomException {
        JSONObject fileObject = fileService.readFile();
        findStatFromCustomers(fileObject.optString("startDate"),fileObject.optString("endDate"));



    }

    private Map<String, List<Object>> findStatFromCustomers(String startDate, String endDate) {

        return dbRepository.getStatFromCustomers(startDate,endDate);
    }
}

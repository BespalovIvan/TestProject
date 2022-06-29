package com.test.db.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.db.CustomException;
import com.test.db.repository.DBRepository;
import com.test.db.service.CustomerService;
import com.test.db.service.FileService;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerStat  implements CustomerService {

    private final FileService fileService;
    private final DBRepository dbRepository;
    private final Map<String, List<Object>> result = new HashMap<>();

    public CustomerStat(FileService fileService, DBRepository dbRepository) {
        this.fileService = fileService;
        this.dbRepository = dbRepository;
    }

    @Override
    public void start() throws CustomException {
        JSONObject fileObject = fileService.readFile();
        result.putAll(findStatFromCustomers(fileObject.optString("startDate"),
                fileObject.optString("endDate")));
    }

    private Map<String, List<Object>> findStatFromCustomers(String startDate, String endDate) {
        Map<String,List<Object>> result = new HashMap<>();
        result.putAll(dbRepository.getTotalDays(startDate,endDate));

        return result;
    }
}

package com.test.db.service.impl;

import com.test.db.domain.dto.ResultDTO;
import com.test.db.domain.dto.SearchResultDTO;
import com.test.db.exception.CustomException;
import com.test.db.repository.SearchRepo;
import com.test.db.factory.CriteriaFactory;
import com.test.db.service.CustomerService;
import com.test.db.service.FileService;
import org.json.JSONArray;
import org.json.JSONObject;

public class CustomerSearch extends CustomerService {

    private final SearchRepo searchRepo;
    private final SearchResultDTO resultDTO = new SearchResultDTO("search");

    public CustomerSearch(FileService fileService, SearchRepo searchRepo) {
        super(fileService);
        this.searchRepo = searchRepo;
    }

    @Override
    public ResultDTO getResult() {
        return resultDTO;
    }

    @Override
    public void readJSONAndFind(JSONObject fileObject) throws CustomException {
        JSONArray criteria = fileObject.optJSONArray("criteria");
        if (criteria == null) throw new CustomException("criteria was not found in json.");

        for (int i = 0; i < criteria.length(); i++) {
            CriteriaFactory.createCustomersFindService(i, criteria, searchRepo).find(resultDTO);
        }
    }
}


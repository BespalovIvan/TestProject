package com.test.db.service.impl;

import com.test.db.domain.ResultDTO;
import com.test.db.domain.SearchResultDTO;
import com.test.db.exception.CustomException;
import com.test.db.repository.DBRepository;
import com.test.db.service.CriteriaFactory;
import com.test.db.service.CustomerService;
import com.test.db.service.FileService;
import org.json.JSONArray;
import org.json.JSONObject;

public class CustomerSearch extends CustomerService {

    private final DBRepository dbRepository;

    private SearchResultDTO resultDTO = new SearchResultDTO("search");

    public CustomerSearch(FileService fileService, DBRepository dbRepository) {
        super(fileService);
        this.dbRepository = dbRepository;
    }

    @Override
    public ResultDTO getResult() {
        return resultDTO;
    }

    @Override
    public void readJSONAndFind(JSONObject fileObject) throws CustomException {
        JSONArray criteria = fileObject.optJSONArray("criteria");
        if (criteria == null) throw new CustomException("Критерий criteria не был найден в json.");

        for (int i = 0; i < criteria.length(); i++) {
            CriteriaFactory.createCustomersFindService(i, criteria, dbRepository).find(resultDTO);
        }

    }

}

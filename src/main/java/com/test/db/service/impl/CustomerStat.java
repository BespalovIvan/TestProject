package com.test.db.service.impl;

import com.test.db.domain.dto.ResultDTO;
import com.test.db.domain.dto.StatResultDTO;
import com.test.db.exception.CustomException;
import com.test.db.repository.DBRepository;
import com.test.db.service.CustomerService;
import com.test.db.service.FileService;
import com.test.db.service.getStatistic.CustomerFindStatImpl;
import org.json.JSONObject;

public class CustomerStat extends CustomerService {

    private final DBRepository dbRepository;
    private final StatResultDTO resultDTO = new StatResultDTO("stat");

    public CustomerStat(FileService fileService, DBRepository dbRepository) {
        super(fileService);
        this.dbRepository = dbRepository;
    }

    @Override
    public ResultDTO getResult() {
        return resultDTO;
    }

    @Override
    public void readJSONAndFind(JSONObject fileObject) throws CustomException {
        new CustomerFindStatImpl(fileObject, dbRepository).findStat(resultDTO);
    }
}

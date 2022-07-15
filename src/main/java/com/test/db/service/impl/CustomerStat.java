package com.test.db.service.impl;

import com.test.db.domain.dto.ResultDTO;
import com.test.db.domain.dto.StatResultDTO;
import com.test.db.exception.CustomException;
import com.test.db.repository.StatRepo;
import com.test.db.service.CustomerService;
import com.test.db.service.FileService;
import com.test.db.service.getStatistic.CustomerFindStatImpl;
import org.json.JSONObject;

public class CustomerStat extends CustomerService {

    private final StatRepo statRepo;
    private StatResultDTO resultDTO = new StatResultDTO("stat");

    public CustomerStat(FileService fileService, StatRepo searchRepo) {
        super(fileService);
        this.statRepo = searchRepo;
    }

    @Override
    public ResultDTO getResult() {
        return resultDTO;
    }

    @Override
    public void readJSONAndFind(JSONObject fileObject) throws CustomException {
        resultDTO = new CustomerFindStatImpl(fileObject, statRepo).findStat();
    }
}

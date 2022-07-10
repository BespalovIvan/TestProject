package com.test.db.service.GetStatistic;

import com.test.db.domain.Customer;
import com.test.db.domain.ResultSearch;
import com.test.db.domain.ResultStat;
import com.test.db.domain.StatResultDTO;
import com.test.db.repository.DBRepository;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerFindStatImpl implements CustomerStatService {

    private final JSONObject criteria;
    private final DBRepository dbRepository;

    public CustomerFindStatImpl(JSONObject criteria, DBRepository dbRepository) {
        this.criteria = criteria;
        this.dbRepository = dbRepository;
    }


    @Override
    public void findStat(StatResultDTO resultDTO) {
        List<Customer> customers = dbRepository.getStat(criteria.optString("startDate"),
                criteria.optString("endDate"));

    }
}

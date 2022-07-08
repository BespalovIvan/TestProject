package com.test.db.service.findCustomers;

import com.test.db.domain.Criteria;
import com.test.db.domain.Customer;
import com.test.db.domain.Result;
import com.test.db.domain.StatResultDTO;
import com.test.db.repository.DBRepository;
import org.json.JSONObject;

import java.util.List;

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

        Criteria criterias = new Criteria(
                "startDate",criteria.optString("startDate"),
                "endDate", criteria.optString("endDate"));

        resultDTO.getResult().add(new Result(criterias,customers));
    }
}

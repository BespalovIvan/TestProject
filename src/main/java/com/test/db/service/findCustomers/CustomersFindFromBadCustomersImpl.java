package com.test.db.service.findCustomers;

import com.test.db.domain.Criteria;
import com.test.db.domain.Customer;
import com.test.db.domain.Result;
import com.test.db.domain.SearchResultDTO;
import com.test.db.repository.DBRepository;
import org.json.JSONObject;

import java.util.List;

public class CustomersFindFromBadCustomersImpl implements CustomersFindService {

    private final JSONObject criteria;
    private final DBRepository dbRepository;

    public CustomersFindFromBadCustomersImpl(JSONObject criteria, DBRepository dbRepository) {
        this.criteria = criteria;
        this.dbRepository = dbRepository;
    }

    @Override
    public void find(SearchResultDTO resultDTO) {
        List<Customer> customers = dbRepository.findBadCustomers(Integer.parseInt(criteria.optString("badCustomers")));
        resultDTO.getResult().add(new Result(new Criteria("badCustomers", criteria.optString("badCustomers")), customers));
    }

}

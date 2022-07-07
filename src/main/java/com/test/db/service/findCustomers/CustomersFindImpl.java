package com.test.db.service.findCustomers;

import com.test.db.domain.Criteria;
import com.test.db.domain.Customer;
import com.test.db.domain.Result;
import com.test.db.domain.SearchResultDTO;
import com.test.db.repository.DBRepository;
import org.json.JSONObject;

import java.util.List;

public class CustomersFindImpl implements CustomersFindService {

    private final JSONObject criteria;
    private final DBRepository dbRepository;

    public CustomersFindImpl(JSONObject criteria, DBRepository dbRepository) {
        this.criteria = criteria;
        this.dbRepository = dbRepository;
    }

    @Override
    public void find(SearchResultDTO resultDTO) {
        List<Customer> customer = dbRepository.findCustomersFromLastName(criteria.optString("lastName"));
        resultDTO.getResult().add(new Result(new Criteria("lastName", criteria.optString("lastName")), customer));
    }

}

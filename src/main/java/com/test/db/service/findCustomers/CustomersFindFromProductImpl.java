package com.test.db.service.findCustomers;

import com.test.db.domain.Customer;
import com.test.db.repository.DBRepository;
import org.json.JSONObject;

import java.util.List;

public class CustomersFindFromProductImpl implements CustomersFindService {

    private final JSONObject criteria;
    private final DBRepository dbRepository;

    public CustomersFindFromProductImpl(JSONObject criteria, DBRepository dbRepository) {
        this.criteria = criteria;
        this.dbRepository = dbRepository;
    }

    @Override
    public List<Customer> find() {
        return dbRepository.findCustomersFromProductNameAndMinCount(criteria.optString("productName"),
                Integer.parseInt(criteria.optString("minTimes")));
    }

}

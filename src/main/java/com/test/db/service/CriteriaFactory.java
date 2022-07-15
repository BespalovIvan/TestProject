package com.test.db.service;

import com.test.db.exception.CustomException;
import com.test.db.repository.SearchRepo;
import com.test.db.service.findCustomers.*;
import org.json.JSONArray;

public class CriteriaFactory {

    public static CustomersFindService createCustomersFindService(int i, JSONArray criteria,
                                                                  SearchRepo searchRepo) throws CustomException {
        if (!criteria.getJSONObject(i).optString("lastName").equals("")) {
            return new CustomersFindImpl(criteria.getJSONObject(i), searchRepo);
        } else if (!criteria.getJSONObject(i).optString("productName").equals("")) {
            return new CustomersFindFromProductImpl(criteria.getJSONObject(i), searchRepo);
        } else if (!criteria.getJSONObject(i).optString("minExpenses").equals("")) {
            return new CustomersFindFromExpensesImpl(criteria.getJSONObject(i), searchRepo);
        } else if (!criteria.getJSONObject(i).optString("badCustomers").equals("")) {
            return new CustomersFindFromBadCustomersImpl(criteria.getJSONObject(i), searchRepo);
        }

        throw new CustomException("invalid search type");
    }
}

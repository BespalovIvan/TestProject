package com.test.db.service;

import com.test.db.CustomException;
import com.test.db.repository.DBRepository;
import com.test.db.service.findCustomers.*;
import org.json.JSONArray;

public class CriteriaFactory {

    public static CustomersFindService createCustomersFindService(int i, JSONArray criteria, DBRepository dbRepository) throws CustomException {
        if (!criteria.getJSONObject(i).optString("lastName").equals("")) {
            return new CustomersFindImpl(criteria.getJSONObject(i), dbRepository);
        } else if (!criteria.getJSONObject(i).optString("productName").equals("")) {
            return new CustomersFindFromProductImpl(criteria.getJSONObject(i), dbRepository);
        } else if (!criteria.getJSONObject(i).optString("minExpenses").equals("")) {
            return new CustomersFindFromExpensesImpl(criteria.getJSONObject(i), dbRepository);
        } else if (!criteria.getJSONObject(i).optString("badCustomers").equals("")) {
            return new CustomersFindFromBadCustomersImpl(criteria.getJSONObject(i), dbRepository);
        }

        throw new CustomException("Неверный тип поиска");
    }

}

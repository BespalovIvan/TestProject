package com.test.db.service.findCustomers;

import com.test.db.domain.Criteria;
import com.test.db.domain.Customer;
import com.test.db.domain.Result;
import com.test.db.domain.SearchResultDTO;
import com.test.db.repository.DBRepository;
import org.json.JSONObject;

import java.util.List;

public class CustomersFindFromExpensesImpl implements CustomersFindService {

    private final JSONObject criteria;
    private final DBRepository dbRepository;

    public CustomersFindFromExpensesImpl(JSONObject criteria, DBRepository dbRepository) {
        this.criteria = criteria;
        this.dbRepository = dbRepository;
    }

    @Override
    public void find(SearchResultDTO resultDTO) {
        List<Customer> customers = dbRepository.findCustomerFromMinAndMaxExpenses(Integer.parseInt(criteria.optString("minExpenses")),
                Integer.parseInt(criteria.optString("maxExpenses")));

        Criteria criterias = new Criteria(
                "minExpenses", criteria.optString("minExpenses"),
                "maxExpenses", criteria.optString("maxExpenses")
        );

        resultDTO.getResult().add(new Result(criterias, customers));
    }

}

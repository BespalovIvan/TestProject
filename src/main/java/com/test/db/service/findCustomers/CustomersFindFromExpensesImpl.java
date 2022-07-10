package com.test.db.service.findCustomers;

import com.test.db.domain.Criteria;
import com.test.db.domain.Customer;
import com.test.db.domain.Result;
import com.test.db.domain.SearchResultDTO;
import com.test.db.repository.DBRepository;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<Criteria,List<Customer>> mapResult = new HashMap<>();
        mapResult.put(criterias,customers);
        Result result = new Result(mapResult);
        resultDTO.getResult().add(result);
    }

}

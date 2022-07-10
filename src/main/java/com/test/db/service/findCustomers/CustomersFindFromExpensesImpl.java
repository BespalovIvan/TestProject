package com.test.db.service.findCustomers;

import com.test.db.domain.Customer;
import com.test.db.domain.ResultSearch;
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
        Map<String,String> mapCriteria = new HashMap<>();
        mapCriteria.put("minExpenses", criteria.optString("minExpenses"));
        mapCriteria.put("maxExpenses", criteria.optString("maxExpenses"));
        ResultSearch resultSearch = new ResultSearch(mapCriteria,customers);
        resultDTO.getResultSearch().add(resultSearch);
    }

}

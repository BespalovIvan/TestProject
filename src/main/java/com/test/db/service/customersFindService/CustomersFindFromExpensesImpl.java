package com.test.db.service.customersFindService;

import com.test.db.domain.entities.Customer;
import com.test.db.domain.results.ResultSearch;
import com.test.db.domain.dto.SearchResultDTO;
import com.test.db.repository.SearchRepo;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomersFindFromExpensesImpl implements CustomersFindService {

    private final JSONObject criteria;
    private final SearchRepo searchRepo;

    public CustomersFindFromExpensesImpl(JSONObject criteria, SearchRepo searchRepo) {
        this.criteria = criteria;
        this.searchRepo = searchRepo;
    }

    @Override
    public void find(SearchResultDTO resultDTO) {
        List<Customer> customers = searchRepo
                .findCustomerFromMinAndMaxExpenses(Integer.parseInt(criteria.optString("minExpenses")),
                Integer.parseInt(criteria.optString("maxExpenses")));
        Map<String,String> mapCriteria = new HashMap<>();
        mapCriteria.put("minExpenses", criteria.optString("minExpenses"));
        mapCriteria.put("maxExpenses", criteria.optString("maxExpenses"));
        ResultSearch resultSearch = new ResultSearch(mapCriteria,customers);
        resultDTO.getResults().add(resultSearch);
    }

}

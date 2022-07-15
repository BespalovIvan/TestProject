package com.test.db.service.findCustomers;

import com.test.db.domain.dto.SearchResultDTO;
import com.test.db.domain.entities.Customer;
import com.test.db.domain.results.ResultSearch;
import com.test.db.repository.SearchRepo;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomersFindFromProductImpl implements CustomersFindService {

    private final JSONObject criteria;
    private final SearchRepo searchRepo;

    public CustomersFindFromProductImpl(JSONObject criteria, SearchRepo searchRepo) {
        this.criteria = criteria;
        this.searchRepo = searchRepo;
    }

    @Override
    public void find(SearchResultDTO resultDTO) {
        List<Customer> customers = searchRepo
                .findCustomersFromProductNameAndMinCount(criteria.optString("productName"),
                        Integer.parseInt(criteria.optString("minTimes")));

        Map<String, String> mapCriteria = new HashMap<>();
        mapCriteria.put("productName", criteria.optString("productName"));
        mapCriteria.put("minTimes", criteria.optString("minTimes"));
        ResultSearch resultSearch = new ResultSearch(mapCriteria, customers);
        resultDTO.getResults().add(resultSearch);
    }

}

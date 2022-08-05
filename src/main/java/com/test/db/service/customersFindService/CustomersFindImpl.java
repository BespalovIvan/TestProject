package com.test.db.service.customersFindService;

import com.test.db.domain.dto.SearchResultDTO;
import com.test.db.domain.entities.Customer;
import com.test.db.domain.results.ResultSearch;
import com.test.db.repository.SearchRepo;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomersFindImpl implements CustomersFindService {

    private final JSONObject criteria;
    private final SearchRepo searchRepo;

    public CustomersFindImpl(JSONObject criteria, SearchRepo searchRepo) {
        this.criteria = criteria;
        this.searchRepo = searchRepo;
    }

    @Override
    public void find(SearchResultDTO resultDTO) {
        List<Customer> customers = searchRepo.findCustomersFromLastName(criteria.optString("lastName"));
        Map<String, String> mapCriteria = new HashMap<>();
        mapCriteria.put("lastName", criteria.optString("lastName"));
        ResultSearch resultSearch = new ResultSearch(mapCriteria, customers);
        resultDTO.getResults().add(resultSearch);
    }
}

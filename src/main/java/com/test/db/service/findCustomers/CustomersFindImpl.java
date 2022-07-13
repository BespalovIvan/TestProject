package com.test.db.service.findCustomers;

import com.test.db.domain.dto.SearchResultDTO;
import com.test.db.domain.entities.Customer;
import com.test.db.domain.results.ResultSearch;
import com.test.db.repository.DBRepository;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<String, String> mapCriteria = new HashMap<>();
        mapCriteria.put("lastName", criteria.optString("lastName"));
        ResultSearch resultSearch = new ResultSearch(mapCriteria, customer);
        resultDTO.getResults().add(resultSearch);
    }
}

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

public class CustomersFindFromProductImpl implements CustomersFindService {

    private final JSONObject criteria;
    private final DBRepository dbRepository;

    public CustomersFindFromProductImpl(JSONObject criteria, DBRepository dbRepository) {
        this.criteria = criteria;
        this.dbRepository = dbRepository;
    }

    @Override
    public void find(SearchResultDTO resultDTO) {
        List<Customer> customers = dbRepository.findCustomersFromProductNameAndMinCount(criteria.optString("productName"),
                Integer.parseInt(criteria.optString("minTimes")));

        Criteria criterias = new Criteria(
                "productName", criteria.optString("productName"),
                "minTimes", criteria.optString("minTimes")
        );
        Map<Criteria,List<Customer>> mapResult = new HashMap<>();
        mapResult.put(criterias,customers);
        Result result = new Result(mapResult);
        resultDTO.getResult().add(result);
    }

}

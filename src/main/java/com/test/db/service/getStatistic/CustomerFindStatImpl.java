package com.test.db.service.getStatistic;

import com.test.db.domain.dto.StatResultDTO;
import com.test.db.repository.DBRepository;
import org.json.JSONObject;

public class CustomerFindStatImpl implements CustomerStatService {

    private final JSONObject desiredDates;
    private final DBRepository dbRepository;

    public CustomerFindStatImpl(JSONObject criteria, DBRepository dbRepository) {
        this.desiredDates = criteria;
        this.dbRepository = dbRepository;
    }

    @Override
    public void findStat(StatResultDTO resultDTO) {
        StatResultDTO result = dbRepository.getStat(desiredDates.optString("startDate"),
                desiredDates.optString("endDate"));
        resultDTO.setTotalDays(result.getTotalDays());
        resultDTO.setCustomers(result.getCustomers());
        resultDTO.setTotalExpenses(result.getTotalExpenses());
        resultDTO.setAvgExpenses(result.getAvgExpenses());
    }
}

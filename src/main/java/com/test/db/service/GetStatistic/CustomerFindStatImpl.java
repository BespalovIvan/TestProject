package com.test.db.service.GetStatistic;

import com.test.db.domain.Customer;
import com.test.db.domain.CustomerForStat;
import com.test.db.domain.ResultStat;
import com.test.db.domain.StatResultDTO;
import com.test.db.repository.DBRepository;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.List;

public class CustomerFindStatImpl implements CustomerStatService {

    private final JSONObject desiredDates;
    private final DBRepository dbRepository;

    public CustomerFindStatImpl(JSONObject criteria, DBRepository dbRepository) {
        this.desiredDates = criteria;
        this.dbRepository = dbRepository;
    }


    @Override
    public void findStat(StatResultDTO resultDTO) {
        List<CustomerForStat> customers = dbRepository.getStat(desiredDates.optString("startDate"),
                desiredDates.optString("endDate"));
        Integer totalDays = dbRepository.getTotalDays(desiredDates.optString("startDate"),
                desiredDates.optString("endDate"));
        Integer totalExpenses = dbRepository.getTotalExpenses(desiredDates.optString("startDate"),
                desiredDates.optString("endDate"));
        BigDecimal avgExpenses = dbRepository.getAVGExpenses(desiredDates.optString("startDate"),
                desiredDates.optString("endDate"));
        ResultStat resultStat = new ResultStat(customers,totalDays,totalExpenses,avgExpenses);
        resultDTO.setResultStats(resultStat);
    }
}

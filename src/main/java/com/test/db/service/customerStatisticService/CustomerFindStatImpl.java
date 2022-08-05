package com.test.db.service.customerStatisticService;

import com.test.db.domain.dto.StatResultDTO;
import com.test.db.exception.CustomException;
import com.test.db.repository.StatRepo;
import org.json.JSONObject;

public class CustomerFindStatImpl implements CustomerStatService {

    private final JSONObject desiredDates;
    private final StatRepo statRepo;

    public CustomerFindStatImpl(JSONObject criteria, StatRepo statRepo) {
        this.desiredDates = criteria;
        this.statRepo = statRepo;
    }

    @Override
    public StatResultDTO findStat() {
        if (desiredDates.optString("startDate").isEmpty() || desiredDates.optString("endDate").isEmpty()) {
            throw new CustomException("dates not found");
        }
        StatResultDTO stat = statRepo.getStat(desiredDates.optString("startDate"),
                desiredDates.optString("endDate"));
        if(stat.getCustomers().isEmpty()){
            throw new CustomException("no customers on the dates");
        }
        return stat;
    }
}

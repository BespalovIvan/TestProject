package com.test.db.service.getStatistic;

import com.test.db.domain.dto.StatResultDTO;
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
        return statRepo.getStat(desiredDates.optString("startDate"),
                desiredDates.optString("endDate"));
    }
}

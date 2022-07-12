package com.test.db.service.GetStatistic;

import com.test.db.domain.ResultStat;
import com.test.db.domain.StatResultDTO;
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
        ResultStat result = dbRepository.getStat(desiredDates.optString("startDate"),
                desiredDates.optString("endDate"));
        resultDTO.setResultStats(result);
    }
}

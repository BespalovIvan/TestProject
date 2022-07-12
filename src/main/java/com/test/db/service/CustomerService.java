package com.test.db.service;

import com.test.db.domain.*;
import com.test.db.exception.CustomException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//Template
public abstract class CustomerService {

    private final FileService fileService;

    protected CustomerService(FileService fileService) {
        this.fileService = fileService;
    }

    public abstract void readJSONAndFind(JSONObject fileObject) throws CustomException;

    public abstract ResultDTO getResult();

    public void start() throws CustomException {
        switch (getResult().getType()) {
            case "search": {
                readJSONAndFind(fileService.readFile());
                List<JSONObject> jsonObjectList = new ArrayList();
                SearchResultDTO searchResultDTO = (SearchResultDTO) getResult();
                List<ResultSearch> resultsList = searchResultDTO.getResultSearch();
                jsonObjectList.add(new JSONObject().put("type", getResult().getType()));
                jsonObjectList.add(new JSONObject().put("results", resultsList));
                String result = jsonObjectList.toString();
                fileService.writeFile(result);
                break;
            }
            case "stat": {
                readJSONAndFind(fileService.readFile());
                List<JSONObject> jsonObjectList = new ArrayList();
                StatResultDTO statResultDTO = (StatResultDTO) getResult();
                ResultStat resultStat = statResultDTO.getResultStats();
                jsonObjectList.add(new JSONObject().put("type", getResult().getType()));
                jsonObjectList.add(new JSONObject().put("totalDays", resultStat.getTotalDays()));
                jsonObjectList.add(new JSONObject().put("customers",resultStat.getCustomers()));
                jsonObjectList.add(new JSONObject().put("totalExpenses", resultStat.getTotalExpenses()));
                jsonObjectList.add(new JSONObject().put("avgExpenses", resultStat.getAvgExpenses()));
                String result = jsonObjectList.toString();
                fileService.writeFile(result);
                break;
            }
        }
    }
}

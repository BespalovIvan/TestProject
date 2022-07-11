package com.test.db.service;

import com.google.gson.Gson;
import com.test.db.domain.*;
import com.test.db.exception.CustomException;
import org.json.JSONObject;

import java.util.*;

//Template
public abstract class CustomerService {

    private final FileService fileService;

    protected CustomerService(FileService fileService) {
        this.fileService = fileService;
    }

    public abstract void readJSONAndFind(JSONObject fileObject) throws CustomException;

    public abstract ResultDTO getResult();

    public void start() throws CustomException {
        switch (getResult().getType()){
            case "search":{
                readJSONAndFind(fileService.readFile());
                List<JSONObject> jsonObjectList = new ArrayList();
                SearchResultDTO searchResultDTO = (SearchResultDTO) getResult();
                List<ResultSearch> resultsList = searchResultDTO.getResultSearch();
                jsonObjectList.add(new JSONObject().put("type",getResult().getType()));
                jsonObjectList.add(new JSONObject().put("results",resultsList));
                String result = jsonObjectList.toString();
                fileService.writeFile(result);
            }
            case "stat":{
                Gson gson = new Gson();
                readJSONAndFind(fileService.readFile());
                List<JSONObject> jsonObjectList = new ArrayList();
                StatResultDTO statResultDTO = (StatResultDTO) getResult();
                ResultStat resultStat = statResultDTO.getResultStats();
                jsonObjectList.add(new JSONObject().put("type",getResult().getType()));
                jsonObjectList.add(new JSONObject().put("customers",gson.toJson(resultStat)));
                String result = jsonObjectList.toString();
                fileService.writeFile(result);
            }
        }
    }
}

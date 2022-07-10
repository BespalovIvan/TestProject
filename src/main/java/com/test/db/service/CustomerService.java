package com.test.db.service;

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
                jsonObjectList.add(new JSONObject().put("type","search"));
                jsonObjectList.add(new JSONObject().put("results",resultsList));
                String result = jsonObjectList.toString();
                fileService.writeFile(result);
            }
            case "stat":{
                readJSONAndFind(fileService.readFile());

            }
        }
    }
}

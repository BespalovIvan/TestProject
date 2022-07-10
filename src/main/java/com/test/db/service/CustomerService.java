package com.test.db.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.test.db.domain.*;
import com.test.db.exception.CustomException;
import org.json.JSONArray;
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
                Gson gson = new Gson();
                SearchResultDTO searchResultDTO = (SearchResultDTO) getResult();
                List<Result> resultsList = searchResultDTO.getResult();
                Collection collection = new ArrayList();
                collection.add(new JSONObject().put("type",getResult().getType()).toString());
                collection.add("results:");
                for (int i = 0; i < resultsList.size(); i++) {
                    Map<Criteria,List<Customer>> mapResult = resultsList.get(i).getResults();
                    Set<Criteria> criteriaSet = mapResult.keySet();
                    List<Customer> customers = new ArrayList<>();
                   for(Criteria c: criteriaSet){
                       customers = mapResult.get(c);
                       collection.add(c);
                    }
                   collection.add("results:");
                   customers.forEach(customer -> collection.add("{" + " \"lastname\": " + gson.toJson(customer.getLastname())+ ", \"firstname\": " + gson.toJson(customer.getFirstname() + "}")));
                }
                String result = gson.toJson(collection);
                fileService.writeFile(result);
            }
            case "stat":{
            }
        }

    }
}

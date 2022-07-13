package com.test.db.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.db.domain.dto.ResultDTO;
import com.test.db.exception.CustomException;
import org.json.JSONObject;

//Template
public abstract class CustomerService {

    private final FileService fileService;

    protected CustomerService(FileService fileService) {
        this.fileService = fileService;
    }

    public abstract void readJSONAndFind(JSONObject fileObject) throws CustomException;

    public abstract ResultDTO getResult();

    public void start() throws CustomException {
        readJSONAndFind(fileService.readFile());
        try {
            fileService.writeFile(new ObjectMapper().writeValueAsString(getResult()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}



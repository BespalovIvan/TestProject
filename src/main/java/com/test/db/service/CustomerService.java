package com.test.db.service;

import com.test.db.domain.ResultDTO;
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
        fileService.writeFile(getResult().toString());
    }

}

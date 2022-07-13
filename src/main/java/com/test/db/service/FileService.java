package com.test.db.service;

import com.test.db.exception.CustomException;
import org.json.JSONObject;

public interface FileService {

    JSONObject readFile() throws CustomException;

    void writeFile(String result);
}

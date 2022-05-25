package com.test.db.service;

import com.test.db.CustomException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

public interface FileService {

    JSONObject readFile() throws IOException, CustomException;

    void writeFile(Map<Integer, Object> result);

}

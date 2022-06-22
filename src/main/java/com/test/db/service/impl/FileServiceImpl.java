package com.test.db.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.db.CustomException;
import com.test.db.domain.Customer;
import com.test.db.service.FileService;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class FileServiceImpl implements FileService {

    private final String inputFile;
    private final String outputFile;

    public FileServiceImpl(String inputFile, String outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    @Override
    public JSONObject readFile() {
        String inputJson;
        try {
            inputJson = new Scanner(new File(inputFile)).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            throw new CustomException(String.format("Файл %s не найден", inputFile));
        }

        if (inputJson == null) throw new CustomException("Не удалось распарсить json");
        return new JSONObject(inputJson);
    }

    @Override
    public void writeFile(Map<String, List<Customer>> result) {
       List<String> keys = new ArrayList<>(result.keySet());
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(outputFile), "criteria : " + keys.get(0) + " "  + result.get("lastName")
            + " criteria : " + keys.get(1) + " "  + result.get("productName"));

        } catch (IOException e) {
            throw new CustomException("Не удалось записать файл");
        }
    }

}
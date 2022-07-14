package com.test.db.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.db.domain.dto.ResultDTO;
import com.test.db.exception.CustomException;
import com.test.db.service.FileService;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

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
    public void writeFile(ResultDTO result) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputFile), result);
        } catch (IOException e) {
            throw new CustomException("Не удалось записать файл");
        }
    }
}



package com.test.db.service.impl;

import com.test.db.ReadFileExeption;
import com.test.db.service.FileService;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class FileServiceImpl implements FileService {

    private final String inputFile;
    private final String outputFile;

    public FileServiceImpl(String inputFile, String outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    @Override
    public Object readFile() throws ReadFileExeption {
        try (FileReader reader = new FileReader(inputFile)) {
            return new JSONParser().parse(reader);
        } catch (ParseException e) {
            throw new ReadFileExeption("parsing error");
        } catch (IOException e) {
            throw new ReadFileExeption("reading error");
        }
    }

    @Override
    public void writeFile(Map<String, Object> result) {
        //логика на запись в файл
    }

}
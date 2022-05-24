package com.test.db.service;

import com.test.db.ReadFileExeption;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Map;

public interface FileService {

    Object readFile() throws IOException, ParseException, ReadFileExeption;

    void writeFile(Map<String, Object> result);

}

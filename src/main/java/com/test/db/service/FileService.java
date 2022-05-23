package com.test.db.service;

import com.test.db.ReadFileExeption;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public interface FileService {

    Object readFile(String fileName) throws IOException, ParseException, ReadFileExeption;

    void writeFile(String filename);

}

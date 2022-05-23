package com.test.db.service.impl;

import com.test.db.ReadFileExeption;
import com.test.db.service.FileService;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;

public class FileServiceImpl implements FileService {

    @Override
    public Object readFile(String filename) throws ReadFileExeption {
            try(FileReader reader = new FileReader(filename)){
                return new JSONParser().parse(reader);
            }catch (ParseException e){
              throw new ReadFileExeption("parsing error");
            }catch (IOException e){
                throw new ReadFileExeption("reading error");
            }
    }

    @Override
    public void writeFile (String filename){

    }
}
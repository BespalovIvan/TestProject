package com.test.db.service.impl;

import com.test.db.ReadFileExeption;
import com.test.db.service.FileService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class FileServiceImpl implements FileService {

    @Override
    public Object readFile(String filename) throws ReadFileExeption {
        JSONParser jsonParser = new JSONParser();
            try(FileReader reader = new FileReader(filename)){
                return jsonParser.parse(reader);
            }catch (ParseException e){
              throw new ReadFileExeption("parsing error");
            }catch (IOException e){
                throw new ReadFileExeption("reading error");
            }
    }

    public static void main(String[] args) {
        FileServiceImpl file = new FileServiceImpl();
        try {
            JSONObject object = (JSONObject) file.readFile("input.json");
            System.out.println(object);
        }catch (ReadFileExeption e){
           e.printStackTrace();
        }
    }






    @Override
    public void writeFile (String filename){

    }
}
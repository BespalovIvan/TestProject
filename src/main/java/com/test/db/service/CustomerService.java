package com.test.db.service;

import com.test.db.ReadFileExeption;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface CustomerService {

    void start() throws ReadFileExeption, IOException, ParseException;

}

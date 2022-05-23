package com.test.db;

import org.json.simple.parser.ParseException;

public class ReadFileExeption  extends Exception {
    public ReadFileExeption() {
        super();
    }

    public ReadFileExeption(String reading_error) {
        super(reading_error);
    }
}


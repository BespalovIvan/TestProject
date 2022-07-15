package com.test.db.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.db.domain.dto.ErrorDTO;

import java.io.File;
import java.io.IOException;

public class CustomException extends RuntimeException {

    public CustomException(String message) {
        super(message);

    }

    public void writeError(String outputFile, ErrorDTO message){
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputFile),message);
        } catch (IOException e) {
            throw new CustomException("file not found");
        }
    }
}


package com.test.db;

import com.test.db.domain.dto.ErrorDTO;
import com.test.db.exception.CustomException;
import com.test.db.factory.SearchFactory;
import com.test.db.service.impl.FileServiceImpl;
import lombok.Getter;

import java.io.File;

@Getter
public class Main {
    public static void main(String[] args) {
        try {
            SearchFactory.createCustomerService(args[0], new FileServiceImpl(args[1], args[2])).start();
        } catch (IndexOutOfBoundsException e) {
            new CustomException(e.getMessage()).writeError(new File("output.json").toString(),
                    new ErrorDTO("error", "arguments must be at least 3"));
        } catch (Exception e) {
            new CustomException(e.getMessage()).writeError(new File("output.json").toString(),
                    new ErrorDTO("error", e.getMessage()));
        }
    }
}
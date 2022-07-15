package com.test.db;

import com.test.db.domain.dto.ErrorDTO;
import com.test.db.exception.CustomException;
import com.test.db.service.impl.FileServiceImpl;

public class Main {

    public static void main(String[] args) {
        try {
            if (args.length < 3) throw new IllegalArgumentException("Количество аргументов не может быть меньше 3-х!");
            SearchFactory.createCustomerService(args[0], new FileServiceImpl(args[1], args[2])).start();
        }
        catch (Exception e){
            CustomException customException = new CustomException(e.getMessage());
            ErrorDTO errorDTO = new ErrorDTO("error",e.getMessage());
            customException.writeError(args[2],errorDTO);
        }
    }
}
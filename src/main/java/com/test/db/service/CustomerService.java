package com.test.db.service;

import com.test.db.CustomException;

import java.io.IOException;

public interface CustomerService {
    void start() throws CustomException, IOException;
}

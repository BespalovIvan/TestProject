package com.test.db.service.impl;

import com.test.db.domain.Customer;
import com.test.db.exception.CustomException;
import com.test.db.repository.DBRepository;
import com.test.db.service.CustomerService;
import com.test.db.service.FileService;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
TODO класс выбивается из концепции общего приложения. Как будто что-то с ним не то.
Он не будет сохранять, пока мы не приведем к общему знаменателю тип обьекта. Тут ты в List добавляешь Object
обьекты. А в классе CustomerSearch хранятся Customer в листе. Так неправильно, нужно иметь один контракт и несколько реализаций.
Сейчас если мы меняем реализацию на эту, она не будет работать, так как логика по сохранению в родительском классе работает
с результирующей коллекцией.

*/
public class CustomerStat extends CustomerService {

    private final DBRepository dbRepository;


    public CustomerStat(FileService fileService, DBRepository dbRepository) {
        super(fileService);
        this.dbRepository = dbRepository;
    }


    @Override
    public void readJSONAndFind(JSONObject fileObject) throws CustomException {
        checkExistAndAdd("stat", (dbRepository.getStat(fileObject.optString("startDate"), fileObject.optString("endDate"))));
    }
}

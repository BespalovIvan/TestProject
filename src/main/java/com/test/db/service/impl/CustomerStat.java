package com.test.db.service.impl;

import com.test.db.domain.ResultDTO;
import com.test.db.domain.StatResultDTO;
import com.test.db.exception.CustomException;
import com.test.db.repository.DBRepository;
import com.test.db.service.CustomerService;
import com.test.db.service.FileService;
import com.test.db.service.GetStatistic.CustomerFindStatImpl;
import org.json.JSONObject;

/*
TODO класс выбивается из концепции общего приложения. Как будто что-то с ним не то.
Он не будет сохранять, пока мы не приведем к общему знаменателю тип обьекта. Тут ты в List добавляешь Object
обьекты. А в классе CustomerSearch хранятся Customer в листе. Так неправильно, нужно иметь один контракт и несколько реализаций.
Сейчас если мы меняем реализацию на эту, она не будет работать, так как логика по сохранению в родительском классе работает
с результирующей коллекцией.

*/
public class CustomerStat extends CustomerService {

    private final DBRepository dbRepository;
    private StatResultDTO resultDTO = new StatResultDTO("stat");

    public CustomerStat(FileService fileService, DBRepository dbRepository) {
        super(fileService);
        this.dbRepository = dbRepository;
    }

    @Override
    public ResultDTO getResult() {
        return resultDTO;
    }

    @Override
    public void readJSONAndFind(JSONObject fileObject) throws CustomException {
        new CustomerFindStatImpl(fileObject,dbRepository).findStat(resultDTO);
    }
}

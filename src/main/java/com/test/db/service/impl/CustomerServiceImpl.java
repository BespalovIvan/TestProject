package com.test.db.service.impl;

import com.test.db.ReadFileExeption;
import com.test.db.domain.Customer;
import com.test.db.repository.DBRepository;
import com.test.db.service.CustomerService;
import com.test.db.service.FileService;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class CustomerServiceImpl implements CustomerService {

    private final FileService fileService;
    private final DBRepository dbRepository;

    public CustomerServiceImpl(FileService fileService, DBRepository dbRepository) {
        this.fileService = fileService;
        this.dbRepository = dbRepository;
    }

    @Override
    public void start() throws ReadFileExeption, IOException, ParseException {
        // определяем какую операцию(метод) вызвать для search из нужных
        JSONObject fileObject = (JSONObject) fileService.readFile();//прочитали файл
        Stream.of(fileObject.get("criterias")).forEach(criterias -> {
            Stream.of((JSONObject) criterias).forEach(criteria -> {

                if (criteria.containsKey("lastName")) { // если ключ критерий содержит название lastName
                    Map<String, Object> customers = findCustomers(criteria.get("lastName").toString());// то вызываем метод который возвращает список покупателей с этой фамилией
                    fileService.writeFile(customers);
                } else if (criteria.containsKey("minExpenses")) { // если ключ критерий содержит название lastName
                    System.out.println();
                }

            });
        });
    }

    /*
    *
    * public void start(String inputFile, String outputFile) throws ReadFileExeption {
        // определяем какую операцию(метод) вызвать из нужных
        JSONObject fileObject = (JSONObject) new FileServiceImpl().readFile(inputFile);//прочитали файл
        JSONArray criterias = (JSONArray)fileObject.get("criterias");// получли масив критериев
        for(Object cr : criterias){// заходим в каждый критерий
            JSONObject critObj = (JSONObject) cr;
            if(critObj.containsKey("lastName")){ // если ключ критерий содержит название lastName
            String LastName = (String) critObj.get("lastName");
            findCustomers(LastName);// то вызываем метод который возвращает список покупателей с этой фамилией
            }
        }
    }
    * */


    public Map<String, Object> findCustomers(String lastName) {
        List<Customer> customersFromLastName = dbRepository.findCustomersFromLastName(lastName);
        Map<String, Object> response = new HashMap<>();
        for (Customer c : customersFromLastName) {
            response.put(c.getLastname(), c.getUsername());
        }
        return response;
    }

}

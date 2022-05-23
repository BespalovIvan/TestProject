package com.test.db.service.impl;

import com.test.db.ReadFileExeption;
import com.test.db.domain.Customer;
import com.test.db.repository.DBRepository;
import com.test.db.service.CustomerService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerServiceImpl implements CustomerService {

    @Override
    public void start(String inputFile, String outputFile) throws ReadFileExeption {
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

    public static Map<String, Object> findCustomers(String lastName) {
        List<Customer> customersFromLastName = new DBRepository().findCustomersFromLastName(lastName);
        Map<String, Object> response = new HashMap<>();
        for(Customer c : customersFromLastName){
            response.put(c.getLastname(),c.getUsername());
        }

        return response;
    }
}

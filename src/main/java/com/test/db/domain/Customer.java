package com.test.db.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;


@Getter
@AllArgsConstructor
public class Customer {

    private Integer id;
    private String firstname;
    private String lastname;
    private Map<String,Integer> purchases;

    public Customer (Integer id,String firstname, String lastname){
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "{" + "lastname: " + lastname + " ," + "firstname: " + firstname + " purchases " + purchases + "}";
    }

}

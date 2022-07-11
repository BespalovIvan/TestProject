package com.test.db.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;


@Getter
@AllArgsConstructor
public class Customer {
    private Integer id;
    private String lastname;
    private String firstname;
    public Customer(String lastname, String firstname) {
        this.lastname = lastname;
        this.firstname = firstname;

    }
}

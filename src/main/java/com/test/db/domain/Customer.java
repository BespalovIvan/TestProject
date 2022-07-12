package com.test.db.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;


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

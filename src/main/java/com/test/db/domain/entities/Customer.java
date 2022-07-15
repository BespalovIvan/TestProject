package com.test.db.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class Customer {

    @JsonIgnore
    private Integer id;
    private String lastname;
    private String firstname;

    public Customer(String lastname, String firstname) {
        this.lastname = lastname;
        this.firstname = firstname;
    }
}

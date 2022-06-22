package com.test.db.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Customer {

    private Integer id;
    private String firstname;
    private String lastname;

    @Override
    public String toString() {
        return "{" + "lastname: " + lastname + " ," + "firstname: " + firstname + "}";
    }

}

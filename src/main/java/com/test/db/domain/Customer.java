package com.test.db.domain;

public class Customer {

    private Integer id;
    private String firstname;
    private String lastname;

    public Customer(Integer id, String username,String lastname){
        this.id = id;
        this.firstname = username;
        this.lastname = lastname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return firstname;
    }

    public void setUsername(String username) {
        this.firstname = username;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{id: " + id
                + ", username: " + firstname
                + ", lastname: " + lastname
                + "}";
    }

}

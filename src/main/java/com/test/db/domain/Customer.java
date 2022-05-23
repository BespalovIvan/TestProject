package com.test.db.domain;

public class Customer {
    private Integer id;
    private String username;
    private String lastname;

    public Customer(){

    }
    public Customer(Integer id, String username,String lastname){
        this.id = id;
        this.username = username;
        this.lastname = lastname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
                + ", username: " + username
                + ", lastname: " + lastname
                + "}";
    }
}

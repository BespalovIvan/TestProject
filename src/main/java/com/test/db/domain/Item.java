package com.test.db.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Item {

    private Integer id;
    private String productName;
    private Integer price;

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                '}';
    }
}

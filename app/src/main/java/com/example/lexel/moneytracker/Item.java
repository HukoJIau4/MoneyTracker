package com.example.lexel.moneytracker;

public class Item {

    private String name;
    private String price;

    Item(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String getPrice() {
        return String.valueOf(price);
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
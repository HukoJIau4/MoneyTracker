package com.example.lexel.moneytracker;

import java.io.Serializable;

public class Item implements Serializable{
    static final String TYPE_UNKNOWN = "unknown";
    static final String TYPE_EXPENSE = "expense";
    static final String TYPE_INCOME = "income";

    public int id;
    int price;
    public String name;
    private String type;

    Item(String name, int price, String type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }



}

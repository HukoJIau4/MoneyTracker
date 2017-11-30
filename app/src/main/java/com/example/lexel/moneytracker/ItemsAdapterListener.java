package com.example.lexel.moneytracker;


import com.example.lexel.moneytracker.api.Item;

public interface ItemsAdapterListener {

    void onItemClick(Item item, int position);
    void onItemLongClick(Item item, int position);


}

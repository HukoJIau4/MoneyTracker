package com.example.lexel.moneytracker;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {

    private List<Item> items = new ArrayList<>();

    ItemsAdapter() {

        items.add(new Item("Молоко", 35 + " \u20BD"));
        items.add(new Item("Желе", 78 + " \u20BD"));
        items.add(new Item("Сковородка с антипригарным покрытием", 2500 + " \u20BD"));
        items.add(new Item("Молоко", 35 + " \u20BD"));
        items.add(new Item("Желе", 78 + " \u20BD"));
        items.add(new Item("Сковородка с антипригарным покрытием", 2500 + " \u20BD"));
        items.add(new Item("Молоко", 35 + " \u20BD"));
        items.add(new Item("Желе", 78 + " \u20BD"));
        items.add(new Item("Сковородка с антипригарным покрытием", 2500 + " \u20BD"));
        items.add(new Item("Молоко", 35 + " \u20BD"));
        items.add(new Item("Желе", 78 + " \u20BD"));
        items.add(new Item("Сковородка с антипригарным покрытием", 2500 + " \u20BD"));
        items.add(new Item("Молоко", 35 + " \u20BD"));
        items.add(new Item("Желе", 78 + " \u20BD"));
        items.add(new Item("Сковородка с антипригарным покрытием", 2500 + " \u20BD"));
        items.add(new Item("Молоко", 35 + " \u20BD"));
        items.add(new Item("Желе", 78 + " \u20BD"));
        items.add(new Item("Сковородка с антипригарным покрытием", 2500 + " \u20BD"));

    }



    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        Item item = items.get(position);
        holder.bind(item);



    }

    @Override
    public int getItemCount() {
        return items.size();
    }




   static class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView price;


        ItemViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.item_name);
            price = itemView.findViewById(R.id.item_price);
        }

            void bind(Item item){

                price.setText(String.valueOf(item.getPrice()));
                name.setText(item.getName());
            }


        }
   }




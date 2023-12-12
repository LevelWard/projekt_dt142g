package com.antonsskafferi.projekt_dt142g;

import jakarta.persistence.criteria.Order;

public class OrderItem {
    private int amount;  //amount of item
    private String name; //name of item

    public OrderItem(int amount, String name){
        this.amount = amount;
        this.name = name;
    }

    public int getAmount(){
        return amount;
    }

    public String getName(){
        return name;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setName(String name) {
        this.name = name;
    }
}

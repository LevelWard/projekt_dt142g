package com.antonsskafferi.projekt_dt142g;


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




}

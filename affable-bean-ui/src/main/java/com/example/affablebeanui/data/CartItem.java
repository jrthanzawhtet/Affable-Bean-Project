package com.example.affablebeanui.data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data

public class CartItem {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private List<Integer> quantityList;

    public CartItem(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public CartItem(){
        quantityList=new ArrayList<>();
    }
}

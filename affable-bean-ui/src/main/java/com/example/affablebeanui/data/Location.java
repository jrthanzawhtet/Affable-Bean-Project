package com.example.affablebeanui.data;

public enum Location {

    MDY(20),YGN(5),
    THANLYIN(10),
    MALAMYING(20),
    KALAW(40);
    private int price;
    Location(int price){
        this.price=price;
    }
    public int getPrice(){
        return this.price;
    }
}

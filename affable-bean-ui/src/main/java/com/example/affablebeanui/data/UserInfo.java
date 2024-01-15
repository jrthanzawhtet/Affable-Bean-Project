package com.example.affablebeanui.data;

import lombok.Data;

@Data
public class UserInfo {
    private String name;
    private String email;
    private String phone;
    private String address;
    private Location location;
    private String creditCardNumber;
    private double amount;
}

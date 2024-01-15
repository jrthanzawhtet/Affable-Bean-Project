package com.example.deliveryservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double totalAmount;
    private LocalDate orderDate;
    private String orderId;
    @ManyToOne(cascade = CascadeType.ALL)
    private UserInfo userInfo;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Product> products=new ArrayList<>();

    public void addProduct(Product product){
        this.products.add(product);
    }
}

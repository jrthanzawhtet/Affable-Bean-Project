package com.example.affablebeanui.data;

import lombok.Builder;
import lombok.Data;

@Data
public class ProductDto {
    private int id;
    private String name;
    private double price;
    private String description;
    private int category_id;
}

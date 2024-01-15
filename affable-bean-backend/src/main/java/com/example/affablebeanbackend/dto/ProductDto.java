package com.example.affablebeanbackend.dto;

import com.example.affablebeanbackend.entity.Category;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {
    private int id;
    private String name;
    private double price;
    private String description;
    private int category_id;
}

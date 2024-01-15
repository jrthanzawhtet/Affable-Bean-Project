package com.example.affablebeanbackend.dao;

import com.example.affablebeanbackend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<Product,Integer> {
}

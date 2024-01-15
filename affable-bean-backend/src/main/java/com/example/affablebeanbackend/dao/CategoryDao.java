package com.example.affablebeanbackend.dao;

import com.example.affablebeanbackend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category,Integer> {
}

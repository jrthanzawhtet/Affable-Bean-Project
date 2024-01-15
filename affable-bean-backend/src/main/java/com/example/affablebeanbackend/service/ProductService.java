package com.example.affablebeanbackend.service;


import com.example.affablebeanbackend.dao.CategoryDao;
import com.example.affablebeanbackend.dao.ProductDao;
import com.example.affablebeanbackend.dto.CategoryDto;
import com.example.affablebeanbackend.dto.ProductDto;
import com.example.affablebeanbackend.entity.Category;
import com.example.affablebeanbackend.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final CategoryDao categoryDao;
    private final ProductDao productDao;

    public List<CategoryDto> findAllCategory(){
        return categoryDao.findAll()
                .stream().map(this::toCategoryDto)
                .collect(Collectors.toList());
    }
    public List<ProductDto> findAllProduct(){
        return productDao.findAll()
                .stream().map(this::toProductDto)
                .collect(Collectors.toList());
    }

    private ProductDto toProductDto(Product product){
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .category_id(product.getCategory().getId())
                .build();
    }
    private CategoryDto toCategoryDto(Category category){
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}

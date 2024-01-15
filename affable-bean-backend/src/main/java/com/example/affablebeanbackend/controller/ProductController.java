package com.example.affablebeanbackend.controller;

import com.example.affablebeanbackend.dto.CategoryDto;
import com.example.affablebeanbackend.dto.ProductDto;
import com.example.affablebeanbackend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/backend")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/product/category-list")
    public List<CategoryDto> listAllCategory(){
        return productService.findAllCategory();
    }
    @GetMapping("/product/product-list")
    public List<ProductDto> listAllProduct(){
        return productService.findAllProduct();
    }

}

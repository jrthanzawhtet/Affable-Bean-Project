package com.example.affablebeanui.config;

import com.example.affablebeanui.data.AccountRequest;
import com.example.affablebeanui.data.CategoryDto;
import com.example.affablebeanui.data.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(url = "${product.backend.url}",value = "product-feign-client")
public interface ProductFeignClient {



    @GetMapping("/backend/product/product-list")
    List<ProductDto> findAllProducts();

    @GetMapping("/backend/product/category-list")
    List<CategoryDto> findAllCategory();
}

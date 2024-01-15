package com.example.deliveryservice.data;

import com.example.deliveryservice.entity.Product;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

/*
   {
   products:[
   {},
   {}
   ],
   "total_amount":35.3,
   "date_process": date
   }
    */
public record OrderProductResponse(
        String name,
        @JsonProperty("total_amount") double totalAmount,
        @JsonProperty("date_process")LocalDate dateProcess,
        List<Product> products
) {
}

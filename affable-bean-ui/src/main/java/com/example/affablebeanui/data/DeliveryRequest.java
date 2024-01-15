package com.example.affablebeanui.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record DeliveryRequest(
        String name,
        String email,
        String phone,
        String address,
        @JsonProperty("total_amount") double totalAmount,
        @JsonProperty("credit_card_number")String creditCardNumber,
        List<ProductRequest> products

) {
}

/*
   {
   "name":"john",
   "email":"john@gmail.com",
   "phone":"55-55-555",
   "address":"Mdy",
   "total_amount":35,
   "credit_card_number":"222222",
   "products":[
       {
       "name":"apple",
       "price":23.4,
       "quantity":3
       },
       {
       "name":"orange",
       "price":22.2,
       "quantity":1
       },
       {
       "name":"banana",
       "price":20.2,
       "quantity":3
       }
    ]

   }
    */


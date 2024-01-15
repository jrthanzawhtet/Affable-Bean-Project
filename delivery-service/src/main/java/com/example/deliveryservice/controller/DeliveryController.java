package com.example.deliveryservice.controller;

import com.example.deliveryservice.data.OrderProductResponse;
import com.example.deliveryservice.entity.OrderProduct;
import com.example.deliveryservice.entity.UserInfo;
import com.example.deliveryservice.service.OrderProductService;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/delivery")
public class DeliveryController {
    private final OrderProductService orderProductService;

    @GetMapping("/checkout/info/{id}")
    public OrderProductResponse getOrderProduct(@PathVariable("id")String id){
        OrderProduct orderProduct= orderProductService
                .findOrderProductByOrderId(id);
        return new OrderProductResponse(
                orderProduct.getUserInfo().getName(),
                orderProduct.getTotalAmount(),
                orderProduct.getOrderDate(),
                orderProduct.getProducts()
        );
    }

    public record ProductRequest(String name,double price,int quantity){}
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
    public record DeliveryRequest(
            String name,
            String email,
            String phone,
            String address,
           @JsonProperty("total_amount") double totalAmount,
           @JsonProperty("credit_card_number") String creditCardNumber,
            List<ProductRequest> products
    ){}
    @PostMapping("/process-delivery")
    public ResponseEntity<String> processDelivery(@RequestBody DeliveryRequest request){
         OrderProduct orderProduct =orderProductService.saveOrderProduct(request);
         return ResponseEntity.ok(orderProduct.getOrderId());
    }

}

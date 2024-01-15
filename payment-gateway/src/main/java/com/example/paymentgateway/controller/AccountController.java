package com.example.paymentgateway.controller;

import com.example.paymentgateway.service.AccountService;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    record AccountRequest(
            @JsonProperty("card_number") String cardNumber
            ,double amount){}

    @PostMapping("/make-payment")
    public ResponseEntity makeTransaction(@RequestBody AccountRequest request){
         accountService.makeTransaction(request.cardNumber,request.amount);
         return ResponseEntity.ok().build();
    }
}
